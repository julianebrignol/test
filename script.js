// exercício 1
// resposta: o js serve para tornar as páginas web interativas. corre no navegador e permite, por exemplo, validar formulários antes de serem enviados, criar animações, mostrar ou esconder elementos da página sem ter de recarregar, e reagir a cliques do utilizador.

// exercício 2
// reposta: o elemento input deve ser criado no HTML, porque o HTML é usado para definir a estrutura da página e os seus elementos, como formulários e campos de texto. o js depois pode o tornar interativo, como validar o que o utilizador escreve ou enviar os dados.

// exercício 3.1
// resposta: '53'

// exercício 3.2
// resposta: 31

// exercício 3.3
// resposta: 31

// exercício 3.4
// resposta: 15

// exercício 3.5
// resposta: não, é '515'

// exercício 3.6
num1 = 1;
num2 = 15;

// exercício 3.7
// resposta: boolean

// exercício 4.1
passwords.push('jubrign0l');

// exercício 4.2
// criar novo array para converter as paswords para string
let passwordsString = passwords.map(password => String(password));
if (passwordsString.includes(userPassword)) {
  alert('Password correta!');
} else {
  alert('Password incorreta.');
}

// exercício 4.3
// converter as passwords para string (String) para conseguir usar o .length
let safePasses = passwords.filter(password => String(password).length > 6);
console.log('safePasses:', safePasses);

// exercício 5.1
studentGrades.forEach((student) => {
  console.log(`A nota de ${student.studentName} é ${student.grade}`);
});

// exercício 5.2
// resposta: dá undefined. o array tem apenas 5 elementos, índices de 0 a 4. portanto, não existe um studentGrades[5].

// exercício 5.3
// resposta: sim

// exercício 6.1
document.querySelector('.achievements').textContent = 'Os meus ensinamentos para a vida';

// exercício 6.2
let listItems = document.querySelectorAll('#myList li');
listItems.forEach(item => {
  item.textContent += ' - com sucesso!';
});

// exercício 6.3
const form = document.querySelector('form');
const ul = document.getElementById('myList');

form.addEventListener('submit' , function(e) {
  e.preventDefault(); // evitar recarregamento da página

  const formData = new FormData(form);
  const newValue = formData.get('myItem'); // obter o valor inserido no input

  const newItem = document.createElement('li');
  newItem.textContent = newValue; // colocar o texto do novo item
  ul.appendChild(newItem);

  form.reset(); // limpar o formulário
});

// exercício 7
// resposta: Grave of the Fireflies

// exercício 8
document.addEventListener('keydown', function(event) {
  if (event.key === 'Escape') {
    alert('Tens a certeza que queres entregar o teste?');
  }
});
