const form = document.getElementById('formCad');

form.addEventListener('submit', async (event) => {
  event.preventDefault();

  const nome = document.getElementById('nome').value;
  const email = document.getElementById('email').value;
  const senha = document.getElementById('senha').value;

  try {
    const response = await fetch('http://localhost:8080/api/usuarios/registrar', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ nome, email, senha }),
    });

    const result = await response.json();
    console.log(result); // Verifique o conteúdo da resposta

    if (response.ok) {
      alert('Usuário registrado com sucesso!');
      form.reset();
      window.location.href = 'login.html';
    } else {
      alert(result || 'Erro ao cadastrar usuário.');
    }
  } catch (error) {
    console.error('Erro na requisição:', error);
    alert('Erro ao conectar ao servidor.');
  }
});
