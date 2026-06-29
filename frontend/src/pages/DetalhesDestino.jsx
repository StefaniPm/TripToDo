import '../styles/detalheDestino.css'
import { useNavigate, useParams } from 'react-router-dom'
import { useState } from 'react'

export default function DetalheDestino() {
    const navigate = useNavigate()
    const { id } = useParams()

    const [abaAtiva, setAbaAtiva] = useState('roteiro')

    const destinos = [
        {
            id: 1,
            cidade: 'Paris',
            pais: 'França'
        },
        {
            id: 2,
            cidade: 'Londres',
            pais: 'Inglaterra'
        },
        {
            id: 3,
            cidade: 'Amsterdã',
            pais: 'Holanda'
        }
    ]

    const destinoAtual = destinos.find((destino) => destino.id === Number(id))

    const roteiro = [
        { dia: 'Dia 1', horario: '09:00', nome: 'Torre Eiffel' },
        { dia: 'Dia 1', horario: '14:00', nome: 'Museu do Louvre' },
        { dia: 'Dia 2', horario: '10:00', nome: 'Arco do Triunfo' }
    ]

    const voos = [
        { empresa: 'Air France', trecho: 'GRU → CDG • 22:00', data: '15/06/2026' },
        { empresa: 'Air France', trecho: 'CDG → GRU • 18:30', data: '25/06/2026' }
    ]

    const hospedagens = [
        { hotel: 'Hotel Le Marais', endereco: 'Rue des Archives, 75004', periodo: 'Check-in: 15/06/2026 • Check-out: 19/06/2026' }
    ]

    const transfers = [
        { tipo: 'Aeroporto → Hotel', data: '15/06/2026 às 08:00' },
        { tipo: 'Hotel → Aeroporto', data: '25/06/2026 às 15:00' }
    ]

    const comidas = [
        { restaurante: 'Le Jules Verne', data: '15/06/2026 às 20:00', tipo: 'Jantar' },
        { restaurante: 'Café de Flore', data: '16/06/2026 às 09:00', tipo: 'Café da Manhã' }
    ]

    const renderConteudo = () => {
        if (abaAtiva === 'roteiro') {
            return roteiro.map((item, index) => (
                <div className="detalhe-item" key={index}>
                    <div className="detalhe-data">
                        <span>{item.dia}</span>
                        <span>{item.horario}</span>
                    </div>

                    <h3>{item.nome}</h3>
                </div>
            ))
        }

        if (abaAtiva === 'voos') {
            return voos.map((item, index) => (
                <div className="detalhe-item detalhe-item-between" key={index}>
                    <div>
                        <h3>{item.empresa}</h3>
                        <p>{item.trecho}</p>
                    </div>

                    <span>{item.data}</span>
                </div>
            ))
        }

        if (abaAtiva === 'hospedagens') {
            return hospedagens.map((item, index) => (
                <div className="detalhe-item" key={index}>
                    <h3>{item.hotel}</h3>
                    <p>{item.endereco}</p>
                    <p>{item.periodo}</p>
                </div>
            ))
        }

        if (abaAtiva === 'transfers') {
            return transfers.map((item, index) => (
                <div className="detalhe-item" key={index}>
                    <h3>{item.tipo}</h3>
                    <p>{item.data}</p>
                </div>
            ))
        }

        if (abaAtiva === 'comidas') {
            return comidas.map((item, index) => (
                <div className="detalhe-item detalhe-item-between" key={index}>
                    <div>
                        <h3>{item.restaurante}</h3>
                        <p>{item.data}</p>
                    </div>

                    <span className="tag-refeicao">{item.tipo}</span>
                </div>
            ))
        }
    }

    return (
        <div className="detalhe-page">
            <header className="detalhe-header">
                <button 
                    type="button"
                    className="detalhe-back-button"
                    onClick={() => navigate(-1)}
                >
                    ← Voltar
                </button>

                <div className="detalhe-header-bottom">
                    <h1>
                        {destinoAtual 
                            ? `${destinoAtual.cidade}, ${destinoAtual.pais}` 
                            : 'Destino'
                        }
                    </h1>

                    <button 
                        type="button"
                        className="detalhe-add-button"
                        onClick={() => navigate(`/destino/${id}/adicionar/${abaAtiva}`)}
                    >
                        + Adicionar
                    </button>
                </div>
            </header>

            <main className="detalhe-content">
                <section className="detalhe-card">
                    <nav className="detalhe-tabs">
                        <button 
                            className={abaAtiva === 'roteiro' ? 'tab active' : 'tab'}
                            onClick={() => setAbaAtiva('roteiro')}
                        >
                            🗓️ Roteiro
                        </button>

                        <button 
                            className={abaAtiva === 'voos' ? 'tab active' : 'tab'}
                            onClick={() => setAbaAtiva('voos')}
                        >
                            ✈️ Voos
                        </button>

                        <button 
                            className={abaAtiva === 'hospedagens' ? 'tab active' : 'tab'}
                            onClick={() => setAbaAtiva('hospedagens')}
                        >
                            🏨 Hospedagens
                        </button>

                        <button 
                            className={abaAtiva === 'transfers' ? 'tab active' : 'tab'}
                            onClick={() => setAbaAtiva('transfers')}
                        >
                            🚗 Transfers
                        </button>

                        <button 
                            className={abaAtiva === 'comidas' ? 'tab active' : 'tab'}
                            onClick={() => setAbaAtiva('comidas')}
                        >
                            🍴 Comidas
                        </button>
                    </nav>

                    <div className="detalhe-lista">
                        {renderConteudo()}
                    </div>
                </section>
            </main>
        </div>
    )
}