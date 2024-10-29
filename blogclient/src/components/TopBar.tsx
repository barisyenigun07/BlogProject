import React from 'react'

const TopBar = () => {
  return (
    <nav className="w-full h-16 bg-slate-700 shadow-2xl flex justify-between items-center text-white">
        <a className="text-3xl font-bold ml-10" href="/">Jotter</a>
        <div className="flex gap-10 mr-10">
          <a href="/register">Kayıt Ol</a>
          <a href="/login">Giriş Yap</a>
        </div>
    </nav>
  )
}

export default TopBar