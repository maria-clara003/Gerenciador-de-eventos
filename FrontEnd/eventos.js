// Função para verificar se o usuário está autenticado
function verificarLogin() {
    const token = localStorage.getItem('token');
    if (!token) {
        // Redireciona para página de login se não estiver autenticado
        window.location.href = '/login.html';
        return false;
    }
    console.log('Token encontrado:', token);  // Log do token
    return true;
}

// Função para carregar eventos do banco
async function recarregarEventos() {
    const autenticado = verificarLogin(); // Verifica se o usuário está autenticado
    if (!autenticado) return;

    const token = localStorage.getItem('token');
    console.log('Token usado para requisição de eventos:', token);  // Log do token usado na requisição

    try {
        console.log('Headers de autorização:', {
            'Authorization': `Bearer ${token}`
        });
        const response = await fetch('http://localhost:8080/api/eventos', {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${token}`
            }
           
        });

        // Log dos headers e resposta
        console.log('Headers enviados:', {
            'Authorization': `Bearer ${token}`
        });

        if (response.ok) {
            const eventos = await response.json();
            const listaEventos = document.getElementById("eventos-lista");
            listaEventos.innerHTML = ''; // Limpa lista atual

            eventos.forEach(evento => {
                const eventoItem = document.createElement("li");
                eventoItem.innerHTML = `
                    <div>
                        <h3>${evento.titulo}</h3>
                        <p>Data: ${evento.data}</p>
                        <p>Descrição: ${evento.descricao}</p>
                        <p>Local: ${evento.local}</p>
                    </div>
                    <div>
                        <button class="btn-editar" onclick="editarEvento('${evento.id}')">Editar</button>
                        <button class="btn-deletar" onclick="deletarEvento('${evento.id}')">Deletar</button>
                    </div>
                `;
                listaEventos.appendChild(eventoItem);
            });

            // Exibe ou oculta o formulário de criação de eventos com base na autenticação
            const formCriarEvento = document.getElementById("form-criar-evento");
            formCriarEvento.style.display = autenticado ? "block" : "none";

        } else {
            alert('Erro ao carregar eventos.');
        }
    } catch (error) {
        console.error('Erro na requisição:', error);
    }
}

// Função para criar um evento
async function adicionarEvento(evento) {
    const token = localStorage.getItem('token');
    if (!token) return;

    const eventoData = {
        titulo: evento.nome,
        descricao: evento.descricao,
        data: evento.data,
        local: evento.local || "Não especificado"
    };

    console.log('Token usado para criar evento:', token);  // Log do token usado para criar evento

    try {
        const response = await fetch('http://localhost:8080/api/eventos', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            },
            body: JSON.stringify(eventoData)
        });

        // Log dos headers e corpo da requisição
        console.log('Headers enviados para criar evento:', {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`,
            'Body': JSON.stringify(eventoData)
        });

        if (response.ok) {
            recarregarEventos(); // Atualiza a lista de eventos
        } else {
            alert('Erro ao adicionar evento.');
        }
    } catch (error) {
        console.error('Erro na requisição:', error);
    }
}

// Função para deletar evento
async function deletarEvento(eventoId) {
    const token = localStorage.getItem('token');
    if (!token) return;

    console.log('Token usado para deletar evento:', token);  // Log do token usado para deletar evento

    try {
        const response = await fetch(`http://localhost:8080/api/eventos/${eventoId}`, {
            method: 'DELETE',
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });

        // Log dos headers da requisição de deletar evento
        console.log('Headers enviados para deletar evento:', {
            'Authorization': `Bearer ${token}`
        });

        if (response.ok) {
            recarregarEventos(); // Atualiza a lista de eventos
        } else {
            alert('Erro ao deletar evento.');
        }
    } catch (error) {
        console.error('Erro na requisição:', error);
    }
}

// Função chamada quando o formulário de criação de evento é enviado
document.getElementById("form-criar-evento").addEventListener("submit", function (e) {
    e.preventDefault();

    const nome = document.getElementById("evento-nome").value;
    const data = document.getElementById("evento-data").value;
    const descricao = document.getElementById("evento-descricao").value;
    const local = document.getElementById("evento-local").value; // Incluído campo 'local'

    // Verificação de campos obrigatórios
    if (!nome || !data || !descricao) {
        alert('Por favor, preencha todos os campos obrigatórios.');
        return;
    }

    const novoEvento = { nome, data, descricao, local };
    adicionarEvento(novoEvento);

    // Limpa os campos do formulário
    document.getElementById("form-criar-evento").reset();
});

// Carrega eventos ao inicializar
window.onload = recarregarEventos;
