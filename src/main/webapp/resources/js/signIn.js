/**
 * 
 */
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

// Ajoute un gestionnaire d'événements de changement à la case à cocher
showPasswordCheckbox.addEventListener('change', function () {
    if (showPasswordCheckbox.checked) {
        // Affiche le mot de passe
        passwordInput.type = 'text';
    } else {
        // Masque le mot de passe
        passwordInput.type = 'password';
    }
});

showPasswordCheckboxSecond.addEventListener('change', function () {
    if (showPasswordCheckboxSecond.checked) {
        // Affiche le mot de passe
        passwordInputSecond.type = 'text';
    } else {
        // Masque le mot de passe
        passwordInputSecond.type = 'password';
    }
});

/*------------------------------------Zone validation des entrées input-----------------------------------------*/

// Fonction pour ajouter la class "error" et déclenchez les vibrations
function showError() {
    passwordInput.classList.add('error');

    // Vibration de l'élément input pendant 200ms
    if (navigator.vibrate) {
        navigator.vibrate(200);
    }
}

// Fonction pour remove la class "error"
function clearError() {
    passwordInput.classList.remove('error');
}

// Ajoute un gestionnaire d'événements à l'élément input
passwordInput.addEventListener('input', function () {
    // Vérifie la longueur du mdp
    if (passwordInput.value.length < 6) {
        // Si la longueur est inférieure à 6 lettres, déclenche la class "error"
        showError();
    } else {
        // Sinon, remove la class "error"
        clearError();
    }
});
