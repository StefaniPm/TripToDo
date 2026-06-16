import '../styles/home.css'
import { useNavigate, useParams } from 'react-router-dom'

export default function Home() {
  const navigate = useNavigate()
  const viagens = [
    {
      id:1,
      titulo: 'Viagem para Paris',
      data: '2024-07-15',
      destino: 'Paris, França',
      status: 'Planejada'
    },
    {
      id: 2,
      titulo: 'Viagem para Nova York',
      data: '2024-08-20',
      destino: 'Nova York, EUA',
      status: 'Em andamento'
    },
    {
      id: 3,
      titulo: 'Viagem para Tóquio',
      data: '2024-09-10',
      destino: 'Tóquio, Japão',
      status: 'Confirmada'
    }
  ]

  return (
    <div className='home-page'>
      <header className='home-header'>
        <h1>TripToDo</h1>

        <button className='logout-button' onClick={() => navigate('/login')} >Sair</button>
      </header>

      <main className='home-content'>
        <div className='home-top'>
          <p>{viagens.length} viagens planejadas</p>

          <button className='add-trip-button' onClick={() => navigate('/nova-viagem')}>+ Nova Viagem</button>
        </div>

        <div className='trips-container'>
          {viagens.map((viagem) => (
            <div className='trip-card' key={viagem.id} onClick={() => navigate(`/roteiro/${viagem.id}`)}>
              <div className='trip-header'>
                <h2>{viagem.titulo}</h2>
                <span className={viagem.status === 'Confirmada'
                                        ? 'status confirmed'
                                        : viagem.status === 'Planejada'
                                        ? 'status planning'
                                        : 'status in-progress'
                                }>
                                    {viagem.status}
                                </span>
              </div>
              <p className='trip-info'>📍 {viagem.destino}</p>
              <p className='trip-info'>📅 {viagem.data}</p>
            </div>
          ))}
        </div>
      </main>
    </div>
  )
}