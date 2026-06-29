import '../styles/cadastro.css'
import { useNavigate } from 'react-router-dom'
import { useState } from 'react'
import axios from "axios";

export default function Cadastro() {
    const navigate = useNavigate()

    const [nome, setNome] = useState('')
    const [email, setEmail] = useState('')
    const [senha, setSenha] = useState('')

    const cadastrar = async (e) => {
        e.preventDefault();

        try {
            await axios.post("http://localhost:8081/usuarios", {
                nome,
                email,
                senha
            });

            alert("Cadastrado com sucesso!");
            navigate("/login");

        } catch (error) {
            console.log(error);
            alert("Erro ao cadastrar");
        }
    };

    return (
        <div className="login-page">
            <div className="login-card">

                <button className="back-button" onClick={() => navigate('/login')}>
                    ⭠ Voltar
                </button>

                <div className="logo-circle">✈</div>
                <h1>Criar Conta</h1>

                <p className="subtitle">Comece a planejar no TripToDo</p>

                <form onSubmit={cadastrar}>
                    
                    <label>Nome</label>
                    <input
                        value={nome}
                        onChange={(e) => setNome(e.target.value)}
                    />

                    <label>Email</label>
                    <input
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                    />

                    <label>Senha</label>
                    <input
                        value={senha}
                        onChange={(e) => setSenha(e.target.value)}
                        type="password"
                    />

                    <button type="submit">Cadastrar</button>

                </form>

            </div>
        </div>
    )
}