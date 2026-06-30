import '../styles/home.css'
import { useNavigate } from 'react-router-dom'
import { useState, useEffect } from 'react'

export default function Home() {
  const navigate = useNavigate()
  const [viagens, setViagens] = useState([])
  const [usuario, setUsuario] = useState(null)

  useEffect(() => {
    const usuarioSalvo = localStorage.getItem('usuarioLogado')

    if (!usuarioSalvo) {
      navigate('/login')
      return
    }

    const usuarioLogado = JSON.parse(usuarioSalvo)
    setUsuario(usuarioLogado)

    const carregarViagens = async () => {
      try {
        const response = await fetch(`http://localhost:8081/viagens/usuario/${usuarioLogado.id}`)

        if (!response.ok) {
          throw new Error('Erro ao carregar viagens')
        }

        const data = await response.json()

        setViagens(data)

      } catch (error) {
        console.error(error)
      }
    }

    carregarViagens()
  }, [navigate])

  const deletarViagem = async (id) => {
    if (!usuario) {
      navigate('/login')
      return
    }

    try {
      const response = await fetch(
        `http://localhost:8081/viagens/${id}?usuarioId=${usuario.id}`,
        {
          method: 'DELETE'
        }
      )

      if (!response.ok) {
        throw new Error('Erro ao excluir viagem')
      }

      setViagens(viagens.filter(v => v.id !== id))

    } catch (error) {
      console.error(error)
      alert('Erro ao excluir viagem')
    }
  }

  const sair = () => {
    localStorage.removeItem('usuarioLogado')
    navigate('/login')
  }

  return (
    <div className='home-page'>
      <header className='home-header'>
        <h1>TripToDo</h1>

        <button className='logout-button' onClick={sair}>Sair</button>
      </header>

      <main className='home-content'>
        <div className='home-top'>
          <p>{viagens.length} viagens planejadas</p>

          <button className='add-trip-button' onClick={() => navigate('/nova-viagem')}>
            + Nova Viagem
          </button>
        </div>

        <div className='trips-container'>
          {viagens.map((viagem) => (
            <div
              className='trip-card'
              key={viagem.id}
              onClick={() => navigate(`/viagem/${viagem.id}/destinos`)}
            >
              <div className='trip-header'>
                <h2>{viagem.nome}</h2>

                <button
                  className='delete-button'
                  type="button"
                  onClick={(e) => {
                    e.stopPropagation()
                    deletarViagem(viagem.id)
                  }}
                >
                  Excluir
                </button>
              </div>

              <p className='trip-info'>
                📝 {viagem.descricao}
              </p>

              <p className='trip-info'>
                📅 Início: {viagem.dataInicio}
              </p>

              <p className='trip-info'>
                📅 Fim: {viagem.dataFim}
              </p>
            </div>
          ))}
        </div>
      </main>
    </div>
  )
}