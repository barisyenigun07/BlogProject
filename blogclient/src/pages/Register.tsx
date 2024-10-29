import React, { useState } from 'react'

const Register = () => {
    const [name, setName] = useState('');
    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [passwordRepeat, setPasswordRepeat] = useState('');

    const inputStyle = "p-2 rounded-full w-full border-slate-700 border-2 shadow-2xl";

    const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        const body = {
            name: name,
            username: username,
            email: email,
            password: password,
            passwordRepeat: passwordRepeat
        };

        console.log(body);

        setName('');
        setUsername('');
        setEmail('');
        setPassword('');
        setPasswordRepeat('');
    }
  return (
    <div className="flex flex-col justify-center w-full items-center mt-12">
        <h3 className="text-3xl text-white">Kayıt Ol</h3>
        <form onSubmit={handleSubmit} className="flex flex-col gap-3 mt-2 p-3 w-1/3">
            <input 
                className={inputStyle} 
                placeholder='İsim' 
                type="text" 
                value={name} 
                onChange={(e) => setName(e.target.value)}
            />
            <input 
                className={inputStyle} 
                placeholder='Kullanıcı Adı' 
                type="text" 
                value={username} 
                onChange={(e) => setUsername(e.target.value)}
            />
            <input 
                className={inputStyle} 
                placeholder='Email' 
                type="text" 
                value={email} 
                onChange={(e) => setEmail(e.target.value)}
            />
            <input 
                className={inputStyle} 
                placeholder='Şifre' 
                type="password" 
                value={password} 
                onChange={(e) => setPassword(e.target.value)}
            />
            <input 
                className={inputStyle} 
                placeholder='Şifre Tekrar' 
                type="password" 
                value={passwordRepeat} 
                onChange={(e) => setPasswordRepeat(e.target.value)}
            />
            <button className="bg-blue-600 w-full p-3 rounded-full text-white shadow-2xl hover:bg-blue-900" type="submit">
                Kayıt Ol
            </button>
        </form>
    </div>
  )
}

export default Register