import '../styles/roteiroViagem.css';
import { useNavigate, useParams } from 'react-router-dom';

export default function RoteiroViagem() {
    const navigate = useNavigate() 
    const {id} = useParams()

    const roteiros = [
        {
            dia: 'Dia 1',
            data: 'domingo, 14 de junho',
            local: 'Paris',
            atividades: [
                { hora: '09:00', nome: 'Torre Eiffel', categoria: 'Passeio' },
                { hora: '14:00', nome: 'Museu do Louvre', categoria: 'Cultura' },
                { hora: '19:00', nome: 'Jantar em Montmartre', categoria: 'Gastronomia' }
            ]
        },
        {
            dia: 'Dia 2',
            data: 'segunda-feira, 15 de junho',
            local: 'Paris',
            atividades: [
                { hora: '10:00', nome: 'Catedral de Notre-Dame', categoria: 'Cultura' },
                { hora: '15:00', nome: 'Passeio pelo Rio Sena', categoria: 'Passeio' }
            ]
        }
    ]

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
        }
    ]

    const viagemAtual = viagens.find(viagem => viagem.id === parseInt(id))

    return (
        <div className='roteiro-page'>
            <header className="roteiro-header">
                <button 
                    type="button"
                    className="roteiro-back-button"
                    onClick={() => navigate('/home')}
                >
                    ← Voltar
                </button>

                <h1>{viagemAtual?.titulo}</h1>
                <p>{viagemAtual?.destino} - {viagemAtual?.data}</p>
            </header>

            <main className="roteiro-content">
                {roteiros.map((roteiro, index) => (
                    <section className="roteiro-card" key={index}>
                        <div className="roteiro-card-header">
                            <div>
                                <h2>{roteiro.dia}</h2>
                                <p>{roteiro.data}</p>
                            </div>

                            <span>📍 {roteiro.local}</span>
                        </div>

                        <div className="linha"></div>

                        <div className="atividades">
                            {roteiro.atividades.map((atividade, i) => (
                                <div className="atividade-card" key={i}>
                                    <div className="atividade-hora">
                                        🕘 {atividade.hora}
                                    </div>

                                    <div>
                                        <h3>{atividade.nome}</h3>
                                        <span>{atividade.categoria}</span>
                                    </div>
                                </div>
                            ))}
                        </div>
                    </section>
                ))}
            </main>
        </div>
    )
}