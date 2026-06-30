import '../styles/novaViagem.css'
import { useNavigate } from 'react-router-dom'
import { useState } from 'react'

export default function NovaViagem() {
    const navigate = useNavigate()

    const [nome, setNome] = useState('')
    const [descricao, setDescricao] = useState('')
    const [dataInicio, setDataInicio] = useState('')
    const [dataFim, setDataFim] = useState('')

    const handleSubmit = async (e) => {
        e.preventDefault()

        const usuarioSalvo = localStorage.getItem('usuarioLogado')

        if (!usuarioSalvo) {
            navigate('/login')
            return
        }

        const usuarioLogado = JSON.parse(usuarioSalvo)

        try {
            const response = await fetch('http://localhost:8081/viagens', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    nome,
                    descricao,
                    dataInicio,
                    dataFim,
                    usuarioId: usuarioLogado.id
                })
            })

            if (!response.ok) {
                throw new Error('Erro ao criar viagem')
            }

            const viagemCriada = await response.json()

            console.log('Viagem criada:', viagemCriada)

            alert('Viagem criada com sucesso!')

            navigate('/home')

        } catch (error) {
            console.error(error)
            alert('Erro ao criar viagem')
        }
    }

    return (
        <div className='nova-page'>
            <header className='nova-header'>
                <button 
                    type='button' 
                    className='nova-back-button' 
                    onClick={() => navigate('/home')}
                >
                    ← Voltar
                </button>
            </header>

            <main className='nova-content'>
                <div className='nova-top'>
                    <h1>Nova Viagem</h1>
                    <p>Preencha os detalhes da sua próxima aventura</p>

                    <form className='nova-form' onSubmit={handleSubmit}>
                        <label>Título da Viagem</label>
                        <input
                            type="text"
                            placeholder="Ex: Viagem para Paris"
                            value={nome}
                            onChange={(e) => setNome(e.target.value)}
                        />

                        <label>Data de início</label>
                        <input
                            type="date"
                            value={dataInicio}
                            onChange={(e) => setDataInicio(e.target.value)}
                        />

                        <label>Data de fim</label>
                        <input
                            type="date"
                            value={dataFim}
                            onChange={(e) => setDataFim(e.target.value)}
                        />

                        <label>Descrição</label>
                        <input
                            type="text"
                            placeholder="Ex: Férias em Paris"
                            value={descricao}
                            onChange={(e) => setDescricao(e.target.value)}
                        />

                        <div className='nova-buttons'>
                            <button 
                                type='button' 
                                className='nova-cancel-button' 
                                onClick={() => navigate('/home')}
                            >
                                Cancelar
                            </button>

                            <button type='submit' className='nova-save-button'>
                                Criar viagem
                            </button>
                        </div>
                    </form>
                </div>
            </main>
        </div>
    )
}