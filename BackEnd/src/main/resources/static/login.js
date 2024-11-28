document.addEventListener('DOMContentLoaded', () => {
  const form = document.getElementById('formLogin'); // Captura o formulário pelo ID

  form.addEventListener('submit', async (event) => {
    event.preventDefault(); // Evitar recarregar a página

    const email = document.getElementById('email').value;
    const senha = document.getElementById('senha').value;

    console.log("Tentando fazer login com email:", email); // Log para depuração

    try {
      const response = await fetch('http://localhost:8080/api/usuarios/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ email, senha }),
      });

      console.log("Resposta do servidor:", response); // Log para depuração

      // Verifica se a resposta foi bem-sucedida
      if (response.ok) {
        const contentType = response.headers.get("Content-Type");

        // Verifica se a resposta é texto (provavelmente um token JWT)
        if (contentType && contentType.includes("text/plain")) {
          const token = await response.text(); // Receber como texto
          console.log("Token recebido:", token); // Log para depuração

          // Armazenar o token no localStorage
          localStorage.setItem('token', token);

          alert('Login realizado com sucesso!');
          window.location.href = 'eventos.html'; // Redirecionar para a página principal
        } else {
          console.error("Resposta inesperada do servidor: não é um token JWT.");
          alert('Resposta inesperada do servidor.');
        }
      } else if (response.status === 401) {
        alert('Credenciais inválidas.');
      } else {
        const errorMessage = await response.text();
        console.error('Erro desconhecido:', errorMessage);
        alert(errorMessage || 'Erro desconhecido.');
      }
    } catch (error) {
      console.error('Erro na requisição:', error);
      alert('Erro ao conectar ao servidor. Verifique sua conexão ou tente novamente mais tarde.');
    }
  });
});
