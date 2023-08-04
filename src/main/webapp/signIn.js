window.addEventListener('load', function () {
    var rightContainer = document.querySelector('#rightContainer');
    var leftContainer = document.querySelector('#leftContainer');
    rightContainer.style.animationDelay = '0.2s';
    rightContainer.classList.add('fade-in');
    leftContainer.classList.add('fade-in');
});

// Création des constantes
const inscriptionButton = document.getElementById('inscriptionButton');
const connexionButton = document.getElementById('connexionButton');
const inscriptionSection = document.getElementById('flexingSectionSecond');
const flexingSection = document.getElementById('flexingSection');
const showPasswordCheckbox = document.getElementById('showPasswordCheckbox');
const passwordInput = document.getElementById('passwordInput');


// Ajoute un gestionnaire d'événements de clic au bouton
inscriptionButton.addEventListener('click', function () {
    // Vérifie si la section d'inscription est déjà affichée
    if (inscriptionSection.style.display === 'none') {
        // Affiche la section d'inscription
        inscriptionSection.style.display = 'flex';
        flexingSection.style.display = "none";
    } else {
        // Masque la section d'inscription
        inscriptionSection.style.display = 'none';
    }
});

// Ajout d' un gestionnaire d'événements de clic au bouton
connexionButton.addEventListener('click', function () {
    // Vérifie si la section d'inscription est déjà affichée
    if (flexingSection.style.display === 'none') {
        // Affiche la section d'inscription
        flexingSection.style.display = 'flex';
        inscriptionSection.style.display = "none";
    } else {
        // Masque la section d'inscription
        flexingSection.style.display = 'none';
    }
});