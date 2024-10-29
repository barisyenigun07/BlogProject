import React, { useState } from 'react'

const Login = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    const body = {
      username: username,
      password: password
    };

    console.log(body);

    setUsername('');
    setPassword('');
  }
  return (
    <div className="flex flex-col justify-center items-center mt-12">
      <h3 className="text-3xl text-white">Giriş Yap</h3>
      <form className="flex flex-col p-3 gap-4 w-1/3" onSubmit={handleSubmit}>
        <input 
          className="w-full rounded-full border-2 shadow-2xl border-slate-500 p-2" 
          type="text" 
          value={username} 
          placeholder='Kullanıcı Adı' 
          onChange={(e) => setUsername(e.target.value)}
        />
        <input 
          className="w-full rounded-full border-2 shadow-2xl border-slate-500 p-2" 
          type="password" 
          value={password} 
          placeholder='Şifre' 
          onChange={(e) => setPassword(e.target.value)}
        />
        <button className="w-full bg-blue-600 rounded-full text-white p-2 hover:bg-blue-900 shadow-2xl" type="submit">
          Giriş Yap
        </button>
      </form>
    </div>
  )
}

export default Login