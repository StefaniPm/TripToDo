import '../styles/destinos.css'
import { useNavigate, useParams } from 'react-router-dom'

export default function Destinos (){
    const navigate = useNavigate()
    const { id } = useParams()

    const viagens = [
        {
            id: 1,
            titulo: 'Viagem para Europa'
        },
        {
            id: 2,
            titulo: 'Viagem para Nova York'
        },
        {
            id: 3,
            titulo: 'Viagem para Tóquio'
        }
    ]

    const destinos = [
        {
            id: 1,
            cidade: 'Paris',
            pais: 'França',
            dias: '4 dias'
        },
        {
            id: 2,
            cidade: 'Londres',
            pais: 'Inglaterra',
            dias: '3 dias'
        },
        {
            id: 3,
            cidade: 'Amsterdã',
            pais: 'Holanda',
            dias: '2 dias'
        }
    ]

    const viagemAtual = viagens.find((viagem) => viagem.id === Number(id))

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

                <h1>{viagemAtual ? viagemAtual.titulo : 'Viagem'}</h1>
            </header>

            <main className="destinos-content">
                <div className="destinos-top">
                    <h2>Destinos</h2>

                    <button className="add-destino-button"
                        onClick={() => navigate(`/viagem/${id}/adicionar/destino`)}>
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
                                <h3>{destino.cidade}</h3>
                                <p>📍 {destino.pais}</p>
                            </div>

                            <span>{destino.dias}</span>
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