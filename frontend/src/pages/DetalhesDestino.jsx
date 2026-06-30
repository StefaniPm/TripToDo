import '../styles/detalheDestino.css'
import { useNavigate, useParams } from 'react-router-dom'
import { useState, useEffect } from 'react'

export default function DetalheDestino() {
    const navigate = useNavigate()
    const { id } = useParams()

    const [abaAtiva, setAbaAtiva] = useState('roteiro')
    const [atividades, setAtividades] = useState([]);
    const [voos, setVoos] = useState([]);
    const [hospedagens, setHospedagens] = useState([]);
    const [transfers, setTransfers] = useState([]);
    const [comidas, setComidas] = useState([]);
    const [destinoAtual, setDestinoAtual] = useState(null);

    useEffect(() => {

        fetch(`http://localhost:8081/atividades/destino/${id}`)
            .then(response => response.json())
            .then(data => {
                console.log(data);
                setAtividades(data);
            })
            .catch(error => console.error(error));

    }, [id]);
    
    useEffect(() => {
        fetch(`http://localhost:8081/destinos/${id}`)
            .then(res => res.json())
            .then(data => setDestinoAtual(data))
            .catch(err => console.error(err));
    }, [id]);

        useEffect(() => {

            fetch(`http://localhost:8081/voos/destino/${id}`)
                .then(response => response.json())
                .then(data => {
                    console.log(data);
                    setVoos(data);
                })
                .catch(error => console.error(error));

        }, [id]);

        useEffect(() => {

        fetch(`http://localhost:8081/hospedagens/destino/${id}`)
            .then(response => response.json())
            .then(data => {
                console.log(data);
                setHospedagens(data);
            })
            .catch(error => console.error(error));

    }, [id]);

        useEffect(() => {

        fetch(`http://localhost:8081/transfers/destino/${id}`)
            .then(response => response.json())
            .then(data => {
                console.log(data);
                setTransfers(data);
            })
            .catch(error => console.error(error));

    }, [id]);

        useEffect(() => {

        fetch(`http://localhost:8081/estabelecimentos/destino/${id}/tipo/RESTAURANTE`)
            .then(response => response.json())
            .then(data => {
                console.log(data);
                setComidas(data);
            })
            .catch(error => console.error(error));

    }, [id]);

    


    const renderConteudo = () => {
      if (abaAtiva === 'roteiro') {

    if (atividades.length === 0) {
        return (
            <p>Nenhuma atividade cadastrada para este destino.</p>
        );
    }

        return atividades.map((atividade) => (
            <div className="detalhe-item" key={atividade.id}>
                <div className="detalhe-data">
                    <span>{atividade.data}</span>
                    <span>{atividade.horario}</span>
                </div>

                <div>
                    <h3>{atividade.titulo}</h3>

                    {atividade.descricao && (
                        <p>{atividade.descricao}</p>
                    )}

                    {atividade.local && (
                        <p>{atividade.local}</p>
                    )}
                </div>
            </div>
        ));
    }

       if (abaAtiva === 'voos') {

    if (voos.length === 0) {
        return <p>Nenhum voo cadastrado.</p>;
    }

        return voos.map((voo) => (
            <div className="detalhe-item detalhe-item-between" key={voo.id}>
                <div>
                    <h3>{voo.companhia}</h3>

                    <p>
                        {voo.aeroportoOrigem} → {voo.aeroportoDestino}
                    </p>

                    <p>
                        Voo: {voo.numeroVoo}
                    </p>
                </div>

                <div>
                    <p>Partida: {voo.dataHoraPartida}</p>
                    <p>Chegada: {voo.dataHoraChegada}</p>
                </div>
            </div>
        ));
    }

        if (abaAtiva === 'hospedagens') {

            if (hospedagens.length === 0) {
                return <p>Nenhuma hospedagem cadastrada.</p>;
            }

                return hospedagens.map((item) => (
                    <div className="detalhe-item" key={item.id}>
                        <h3>{item.nome}</h3>

                        <p>{item.endereco}</p>

                        <p>
                            Check-in: {item.checkIn}
                        </p>

                        <p>
                            Check-out: {item.checkOut}
                        </p>

                        {item.observacao && (
                            <p>{item.observacao}</p>
                        )}
                    </div>
                ));
            }

       if (abaAtiva === 'transfers') {

            if (transfers.length === 0) {
                return <p>Nenhum transfer cadastrado.</p>;
            }

            return transfers.map((item) => (
                <div className="detalhe-item" key={item.id}>
                    <h3>{item.tipo}</h3>

                    <p>
                        {item.localOrigem} → {item.localDestino}
                    </p>

                    <p>
                        {item.dataHora}
                    </p>

                    {item.observacao && (
                        <p>{item.observacao}</p>
                    )}
                </div>
            ));
        }

        if (abaAtiva === 'comidas') {

            if (comidas.length === 0) {
                return <p>Nenhum restaurante cadastrado.</p>;
            }

            return comidas.map((item) => (
                <div className="detalhe-item" key={item.id}>
                    <h3>{item.nome}</h3>

                    <p>{item.endereco}</p>

                    {item.observacao && (
                        <p>{item.observacao}</p>
                    )}

                   
                </div>
            ));
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