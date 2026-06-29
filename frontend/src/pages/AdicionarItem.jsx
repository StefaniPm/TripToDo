import '../styles/adiocionarItem.css'
import { useParams, useNavigate } from 'react-router-dom'

export default function AdicionarItem() {
    const navigate = useNavigate()
    const { id, tipo } = useParams()

    const titulos = {
        destino: 'Adicionar Destino',
        roteiro: 'Adicionar Atividade',
        voos: 'Adicionar Voo',
        hospodagens: 'Adicionar Hospedagem',
        transfers: 'Adicionar Transfer',
        comida: 'Adicionar Restaurante'
    }

    const botaoTexto = {
        destino: 'Salvar Destino',
        roteiro: 'Salvar Atividade',
        voos: 'Salvar Voo',
        hospedagens: 'Salvar Hospedagem',
        transfers: 'Salvar Transfer',
        comidas: 'Salvar Restaurante'
    }


    //POR ENQUANTO ESSES METODOS COMENTADOS NAO PRECISAM SER UTILIZADOS, DO JEITO QUE ESTA FUNCIONA
    
    // const voltarParaPaginaAnterior = () => {
    //     if (tipo === 'destino') {
    //         navigate(`/viagem/${id}/destinos`)
    //     } else {
    //         navigate(`/destino/${id}`)
    //     }
    // }

    // const handleSubmit = (e) => {
    //     e.preventDefault()
    //     voltarParaPaginaAnterior()
    // }

    const renderCampos = () => {
        if (tipo === 'destino') {
            return (
                <>
                    <label>Cidade</label>
                    <input type="text" placeholder="Ex: Roma" />

                    <label>País</label>
                    <input type="text" placeholder="Ex: Itália" />

                    <label>Quantidade de dias</label>
                    <input type="text" placeholder="Ex: 3 dias" />
                </>
            )
        }
        if (tipo === 'roteiro') {
            return (
                <>
                    <label>Nome da Atividade</label>
                    <input type="text" placeholder="Ex: Torre Eiffel" />

                    <label>Data</label>
                    <input type="date" />

                    <label>Horário</label>
                    <input type="time" />

                    <label>Categoria</label>
                    <input type="text" placeholder="Ex: Passeio, Cultura, Gastronomia" />
                </>
            )
        }

        if (tipo === 'voos') {
            return (
                <>
                    <label>Companhia aérea</label>
                    <input type="text" placeholder="Ex: Air France" />

                    <label>Trecho</label>
                    <input type="text" placeholder='Ex: GRU → CDG' />

                    <label>Data</label>
                    <input type="date" />

                    <label>Horário</label>
                    <input type="time" />
                </>
            )
        }

        if (tipo === 'hospedagens') {
            return (
                <>
                    <label>Nome do Hotel</label>
                    <input type="text" placeholder="Ex: Hotel Le Marais" />

                    <label>Endereço</label>
                    <input type="text" placeholder="Ex: Rue des Archives, 75004" />

                    <label>Check-in</label>
                    <input type="date" />

                    <label>Check-out</label>
                    <input type="date" />
                </>
            )
        }

        if (tipo === 'transfers') {
            return (
                <>
                    <label>Tipo de Transfer</label>
                    <input type="text" placeholder="Ex: Aeroporto → Hotel" />

                    <label>Data</label>
                    <input type="date" />

                    <label>Horário</label>
                    <input type="time" />

                    <label>Observação</label>
                    <input type="text" placeholder="Ex: Motorista particular" />
                </>
            )
        }

        if (tipo === 'comidas') {
            return (
                <>
                    <label>Nome do Restaurante</label>
                    <input type="text" placeholder="Ex: Le Jules Verne" />

                    <label>Tipo de Refeição</label>
                    <input type="text" placeholder="Ex: Jantar, almoço, café da manhã" />

                    <label>Data</label>
                    <input type="date" />

                    <label>Horário</label>
                    <input type="time" />
                </>
            )
        }

        return (
            <p className="adicionar-error">
                Tipo de item não encontrado.
            </p>
        )
    }

    return (
        <div className="adicionar-page">
            <header className="adicionar-header">
                <button
                    type="button"
                    className="adicionar-back-button"
                    onClick={() => navigate(-1)}
                >
                    ← Voltar
                </button>
            </header>

            <main className="adicionar-content">
                <section className="adicionar-card">
                    <h1>{titulos[tipo] || 'Adicionar Item'}</h1>
                    <p>Preencha as informações abaixo</p>

                    <form onSubmit={handleSubmit}>
                        {renderCampos()}

                        <div className="adicionar-buttons">
                            <button
                                type="button"
                                className="cancelar-button"
                                onClick={() => navigate(-1)}
                            >
                                Cancelar
                            </button>

                            <button
                                type="submit"
                                className="salvar-button"
                            >
                                {botaoTexto[tipo] || 'Salvar'}
                            </button>
                        </div>
                    </form>
                </section>
            </main>
        </div>
    )

}