import '../styles/login.css'
import { useNavigate } from 'react-router-dom'
import { useState } from 'react'
import axios from 'axios'

export default function Login() {
    const navigate = useNavigate()
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const [erro, setErro] = useState('')

    const handleSubmit = async (e) => {
    e.preventDefault()

    try {

        await axios.post(
            'http://localhost:8081/usuarios/login',
            {
                email: email,
                senha: password
            }
        )

        navigate('/home')

    } catch (error) {

        setErro('Email ou senha inválidos')

    }
}

    return (
        <div className="login-page">
            <div className="login-card">
                <div className="logo-circle">✈</div>
                <h1>TripToDo</h1>
                <p className="subtitle">Gerencie suas viagens com facilidade</p>

                <form onSubmit={handleSubmit}>
                    <label>Email</label>
                    <input 
                        type="email" 
                        placeholder="seu@email.com" 
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                    />
                    <label>Senha</label>
                    <input 
                        type="password" 
                        placeholder="••••••••"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                    />

                    {erro && <p className="error">{erro}</p>}

                    <button>Entrar</button>
                </form>
                
                <p className="signup-link">Não tem uma conta? {' ' } <span onClick={() => navigate('/cadastro')}>Cadastre-se</span></p>
            </div>
        </div>
    )
}