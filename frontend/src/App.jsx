import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom'
import Home from './pages/Home';
import Login from './pages/Login';
import Cadastro from './pages/Cadastro';
import NovaViagem from './pages/NovaViagem';
import RoteiroViagem from './pages/RoteiroViagem';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Navigate to="/login" />} />
        <Route path="/login" element={<Login />} />
        <Route path="/cadastro" element={<Cadastro />} />
        <Route path="/home" element={<Home />} />
        <Route path="/nova-viagem" element={<NovaViagem />} />
        <Route path="/roteiro/:id" element={<RoteiroViagem />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;