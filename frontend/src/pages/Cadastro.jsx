import '../styles/cadastro.css'
import { useNavigate } from 'react-router-dom'

export default function Cadastro() {
    const navigate = useNavigate()
    
    return (
        <div className="login-page">
            <div className="login-card">
                <button className="back-button" onClick={() => navigate('/login')}>⭠ Voltar</button>
                <div className="logo-circle">✈</div>
                <h1>Criar Conta</h1>
                
                <p className="subtitle">Comece a planejar no TripToDo</p>

                <form>
                    <label>Nome</label>
                    <input type="text" placeholder="Seu nome completo" />
                    <label>Email</label>
                    <input type="email" placeholder="seu@email.com" />
                    <label>Senha</label>
                    <input type="password" placeholder="••••••••"/>

                    <button>Cadastrar</button>
                </form>
                
            </div>
        </div>
    )
}