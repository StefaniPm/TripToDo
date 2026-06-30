import '../styles/adiocionarItem.css'
import { useParams, useNavigate } from 'react-router-dom'
import { useState } from 'react'

export default function AdicionarItem() {
    const navigate = useNavigate()
    const { id, tipo } = useParams()
    const [nome, setNome] = useState('')
    const [cidade, setCidade] = useState('')
    const [pais, setPais] = useState('')
    const [ dataChegada, setDataChegada] = useState ('')
    const [ dataSaida, setDataSaida] = useState('') 
    const [titulo, setTitulo] = useState('');
    const [descricao, setDescricao] = useState('');
    const [data, setData] = useState('');
    const [horario, setHorario] = useState('');
    const [local, setLocal] = useState('');
    const [companhia, setCompanhia] = useState('');
    const [numeroVoo, setNumeroVoo] = useState('');
    const [aeroportoOrigem, setAeroportoOrigem] = useState('');
    const [aeroportoDestino, setAeroportoDestino] = useState('');
    const [dataHoraPartida, setDataHoraPartida] = useState('');
    const [dataHoraChegada, setDataHoraChegada] = useState('');
    const [nomeHospedagem, setNomeHospedagem] = useState('');
    const [endereco, setEndereco] = useState('');
    const [checkIn, setCheckIn] = useState('');
    const [checkOut, setCheckOut] = useState('');
    const [observacao, setObservacao] = useState('');
 
    const [tipoTransfer, setTipoTransfer] = useState('');
    const [localOrigem, setLocalOrigem] = useState('');
    const [localDestino, setLocalDestino] = useState('');
    const [dataHoraTransfer, setDataHoraTransfer] = useState('');
    const [observacaoTransfer, setObservacaoTransfer] = useState('');

    const [nomeRestaurante, setNomeRestaurante] = useState('');
    const [enderecoRestaurante, setEnderecoRestaurante] = useState('');
    const [observacaoRestaurante, setObservacaoRestaurante] = useState('');


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


    const renderCampos = () => {
        if (tipo === 'destino') {
            return (
                <>
                    <label>Nome do destino</label>
                    <input type="text" placeholder="Ex: Centro Histórico" value= {nome} onChange={ (e) => setNome (e.target.value)}  />

                    <label>Cidade</label>
                    <input type="text" placeholder="Ex: Roma" value={cidade} onChange={(e) => setCidade(e.target.value)} />

                    <label>País</label>
                    <input type="text" placeholder="Ex: Itália" value={pais} onChange={(e) => setPais(e.target.value)} />

                    <label>Data de chegada</label>
                    <input type="date" value={dataChegada} onChange={(e) => setDataChegada (e.target.value)} />

                    <label>Data de saída</label>
                    <input type="date" value={dataSaida} onChange={(e) => setDataSaida (e.target.value)} />

                </>
            )
        }

        
        if (tipo === 'roteiro') {
            return (
                <>
                    <label>Título</label>
                    <input
                        type="text"
                        placeholder="Ex: Visitar Coliseu"
                        value={titulo}
                        onChange={(e) => setTitulo(e.target.value)}
                    />

                    <label>Descrição</label>
                    <input
                        type="text"
                        placeholder="Ex: Passeio guiado"
                        value={descricao}
                        onChange={(e) => setDescricao(e.target.value)}
                    />

                    <label>Data</label>
                    <input
                        type="date"
                        value={data}
                        onChange={(e) => setData(e.target.value)}
                    />

                    <label>Horário</label>
                    <input
                        type="time"
                        value={horario}
                        onChange={(e) => setHorario(e.target.value)}
                    />

                    <label>Local</label>
                    <input
                        type="text"
                        placeholder="Ex: Coliseu"
                        value={local}
                        onChange={(e) => setLocal(e.target.value)}
                    />
                </>
            )
        }

        if (tipo === 'voos') {
            return (
                <>
                    <label>Companhia</label>
                    <input
                        type="text"
                        value={companhia}
                        onChange={(e) => setCompanhia(e.target.value)}
                    />

                    <label>Número do voo</label>
                    <input
                        type="text"
                        value={numeroVoo}
                        onChange={(e) => setNumeroVoo(e.target.value)}
                    />

                    <label>Aeroporto de origem</label>
                    <input
                        type="text"
                        value={aeroportoOrigem}
                        onChange={(e) => setAeroportoOrigem(e.target.value)}
                    />

                    <label>Aeroporto de destino</label>
                    <input
                        type="text"
                        value={aeroportoDestino}
                        onChange={(e) => setAeroportoDestino(e.target.value)}
                    />

                    <label>Data e hora da partida</label>
                    <input
                        type="datetime-local"
                        value={dataHoraPartida}
                        onChange={(e) => setDataHoraPartida(e.target.value)}
                    />

                    <label>Data e hora da chegada</label>
                    <input
                        type="datetime-local"
                        value={dataHoraChegada}
                        onChange={(e) => setDataHoraChegada(e.target.value)}
                    />
                </>
            );
        }

        if (tipo === 'hospedagens') {
            return (
                <>
                    <label>Nome da hospedagem</label>
                    <input
                        type="text"
                        value={nomeHospedagem}
                        onChange={(e) => setNomeHospedagem(e.target.value)}
                    />

                    <label>Endereço</label>
                    <input
                        type="text"
                        value={endereco}
                        onChange={(e) => setEndereco(e.target.value)}
                    />

                    <label>Check-in</label>
                    <input
                        type="date"
                        value={checkIn}
                        onChange={(e) => setCheckIn(e.target.value)}
                    />

                    <label>Check-out</label>
                    <input
                        type="date"
                        value={checkOut}
                        onChange={(e) => setCheckOut(e.target.value)}
                    />

                    <label>Observação</label>
                    <input
                        type="text"
                        value={observacao}
                        onChange={(e) => setObservacao(e.target.value)}
                    />
                </>
            );
        }

        if (tipo === 'transfers') {
            return (
                <>
                    <label>Tipo</label>
                    <input
                        type="text"
                        value={tipoTransfer}
                        onChange={(e) => setTipoTransfer(e.target.value)}
                    />

                    <label>Local de origem</label>
                    <input
                        type="text"
                        value={localOrigem}
                        onChange={(e) => setLocalOrigem(e.target.value)}
                    />

                    <label>Local de destino</label>
                    <input
                        type="text"
                        value={localDestino}
                        onChange={(e) => setLocalDestino(e.target.value)}
                    />

                    <label>Data e hora</label>
                    <input
                        type="datetime-local"
                        value={dataHoraTransfer}
                        onChange={(e) => setDataHoraTransfer(e.target.value)}
                    />

                    <label>Observação</label>
                    <input
                        type="text"
                        value={observacaoTransfer}
                        onChange={(e) => setObservacaoTransfer(e.target.value)}
                    />
                </>
            );
        }

       if (tipo === 'comidas') {
            return (
                <>
                    <label>Nome</label>
                    <input
                        type="text"
                        value={nomeRestaurante}
                        onChange={(e) => setNomeRestaurante(e.target.value)}
                    />

                    <label>Endereço</label>
                    <input
                        type="text"
                        value={enderecoRestaurante}
                        onChange={(e) => setEnderecoRestaurante(e.target.value)}
                    />

                    <label>Observação</label>
                    <input
                        type="text"
                        value={observacaoRestaurante}
                        onChange={(e) => setObservacaoRestaurante(e.target.value)}
                    />
                </>
            );
        }

        return (
            <p className="adicionar-error">
                Tipo de item não encontrado.
            </p>
        )
    }

    const handleSubmit = async (e) => {
    e.preventDefault();

        try {

            if (tipo === 'destino') {

                const response = await fetch('http://localhost:8081/destinos', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        nome,
                        cidade,
                        pais,
                        dataChegada,
                        dataSaida,
                        viagemId: Number(id)
                    })
                });

                if (!response.ok) {
                    throw new Error('Erro ao criar destino');
                }

                alert('Destino criado com sucesso!');
                navigate(`/viagem/${id}/destinos`);
                return;
            }

            if (tipo === 'roteiro') {

                const response = await fetch('http://localhost:8081/atividades', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        titulo,
                        descricao,
                        data,
                        horario,
                        local,
                        destinoId: Number(id)
                    })
                });

                console.log(response.status);

                const texto = await response.text();
                console.log(texto);

                if (!response.ok) {
                    throw new Error('Erro ao criar atividade');
                }

                alert('Atividade criada com sucesso!');
                navigate(`/destino/${id}`);
                return;
            }

            if (tipo === 'voos') {

                const response = await fetch('http://localhost:8081/voos', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        companhia,
                        numeroVoo,
                        aeroportoOrigem,
                        aeroportoDestino,
                        dataHoraPartida,
                        dataHoraChegada,
                        destinoId: Number(id)
                    })
                });

                if (!response.ok) {
                    throw new Error('Erro ao criar voo');
                }

                alert('Voo criado com sucesso!');
                navigate(`/destino/${id}`);
                return;
            }

            if (tipo === 'hospedagens') {

                const response = await fetch('http://localhost:8081/hospedagens', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        nome: nomeHospedagem,
                        endereco,
                        checkIn,
                        checkOut,
                        observacao,
                        destinoId: Number(id)
                    })
                });

                console.log(response.status);

                const texto = await response.text();
                console.log(texto);

                if (!response.ok) {
                    throw new Error('Erro ao criar hospedagem');
                }

                alert('Hospedagem criada com sucesso!');
                navigate(`/destino/${id}`);
                return;
            }

            if (tipo === 'transfers') {

                const response = await fetch('http://localhost:8081/transfers', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        tipo: tipoTransfer,
                        localOrigem,
                        localDestino,
                        dataHora: dataHoraTransfer,
                        observacao: observacaoTransfer,
                        destinoId: Number(id)
                    })
                });

                console.log(response.status);

                const texto = await response.text();
                console.log(texto);

                if (!response.ok) {
                    throw new Error('Erro ao criar transfer');
                }

                alert('Transfer criado com sucesso!');
                navigate(`/destino/${id}`);
                return;
            }

            if (tipo === 'comidas') {

                const response = await fetch('http://localhost:8081/estabelecimentos', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        nome: nomeRestaurante,
                        endereco: enderecoRestaurante,
                        observacao: observacaoRestaurante,
                        tipo: "RESTAURANTE",
                        destinoId: Number(id)
                    })
                });

                console.log(response.status);

                const texto = await response.text();
                console.log(texto);

                if (!response.ok) {
                    throw new Error('Erro ao criar restaurante');
                }

                alert('Restaurante criado com sucesso!');
                navigate(`/destino/${id}`);
                return;
            }

        } catch (error) {
            console.error(error);
            alert(error.message);
        }
    };

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