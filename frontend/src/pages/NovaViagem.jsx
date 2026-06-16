import '../styles/novaViagem.css';
import { useNavigate } from 'react-router-dom';

export default function NovaViagem() {
    const navigate = useNavigate()
    const handleSubmit = (e) => {
        e.preventDefault()
        navigate('/novaViagem')
    }

    return (
        <div className='nova-page'>
            <header className='nova-header'>
                <button type='button' className='nova-back-button' onClick={() => navigate('/home')}>← Voltar</button>
            </header>

            <main className='nova-content'>
                <div className='nova-top'>
                    <h1>Nova Viagem</h1>
                    <p>Preencha os detalhes da sua próxima aventura</p>

                    <form className='nova-form' onSubmit={handleSubmit}>
                        <label>Título da Viagem</label>
                        <input type="text" placeholder="Ex: Viagem para Paris" />

                        <label>Data de inicio</label>
                        <input type="date" />

                        <label>Destino</label>
                        <input type="text" placeholder="Ex: Paris, França" />

                        <div className='nova-buttons'>
                            <button type='button' className='nova-cancel-button' onClick={() => navigate('/home')}>Cancelar</button>
                            <button type='submit' className='nova-save-button'>Criar viagem</button>
                        </div>
                    </form>
                </div>
            </main>
        </div>
    )

}