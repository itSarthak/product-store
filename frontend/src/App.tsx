import './App.css'
import Navbar from "./components/Navbar"
import { Routes, Route } from "react-router"
import HomePage from './pages/HomePage'

function App() {
  return (
    <div>
      <Navbar />
      <Routes>
        <Route path='/' element={<HomePage />} />
      </Routes>
    </div>
    
  )
}

export default App
