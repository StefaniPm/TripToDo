import '../styles/destinos.css'
import { useNavigate, useParams } from 'react-router-dom'
import { useState, useEffect } from 'react'

export default function Destinos() {
    const navigate = useNavigate()
    const { id } = useParams()

    const [viagemAtual, setViagemAtual] = useState(null)
    const [destinos, setDestinos] = useState([])

    useEffect(() => {
        const usuarioSalvo = localStorage.getItem('usuarioLogado')

        if (!usuarioSalvo) {
            navigate('/login')
            return
        }

        const usuarioLogado = JSON.parse(usuarioSalvo)

        const carregarDados = async () => {
            try {
                const responseViagem = await fetch(`http://localhost:8081/viagens/${id}?usuarioId=${usuarioLogado.id}`)

                if (!responseViagem.ok) {
                    throw new Error('Viagem não encontrada para este usuário')
                }

                const viagem = await responseViagem.json()
                setViagemAtual(viagem)

                const responseDestinos = await fetch(`http://localhost:8081/destinos/viagem/${id}`)

                if (!responseDestinos.ok) {
                    throw new Error('Erro ao carregar destinos')
                }

                const destinos = await responseDestinos.json()
                setDestinos(destinos)

            } catch (error) {
                console.error(error)
                alert('Você não tem acesso a essa viagem.')
                navigate('/home')
            }
        }

        carregarDados()
    }, [id, navigate])

    return (
        <div className="destinos-page">
            <header className="destinos-header">
                <button 
                    type="button"
                    className="destinos-back-button"
                    onClick={() => navigate('/home')}
                >
                    ← Voltar
                </button>

                <h1>{viagemAtual ? viagemAtual.nome : 'Viagem'}</h1>
            </header>

            <main className="destinos-content">
                <div className="destinos-top">
                    <h2>Destinos</h2>

                    <button 
                        className="add-destino-button"
                        onClick={() => navigate(`/viagem/${id}/adicionar/destino`)}
                    >
                        + Adicionar Destino
                    </button>
                </div>

                <div className="destinos-list">
                    {destinos.map((destino, index) => (
                        <div 
                            className="destino-card" 
                            key={destino.id}
                            onClick={() => navigate(`/destino/${destino.id}`)}
                        >
                            <div className="destino-number">
                                {index + 1}
                            </div>

                            <div className="destino-info">
                                <h3>{destino.nome}</h3>

                                <p>📍 {destino.cidade}, {destino.pais}</p>

                                <p>📅 {destino.dataChegada}</p>

                                <p>📅 {destino.dataSaida}</p>
                            </div>
                        </div>
                    ))}
                </div>

                <button 
                    className="ver-roteiro-button"
                    onClick={() => navigate(`/roteiro/${id}`)}
                >
                    Ver Roteiro Completo
                </button>
            </main>
        </div>
    )
}