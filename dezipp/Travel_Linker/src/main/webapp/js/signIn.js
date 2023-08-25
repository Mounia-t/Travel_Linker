window.addEventListener('load', function () {
    var rightContainer = document.querySelector('#rightContainer');
    var leftContainer = document.querySelector('#leftContainer');
    rightContainer.style.animationDelay = '0.2s';
    rightContainer.classList.add('fade-in');
    leftContainer.classList.add('fade-in');
});

// Création des constantes
const inscriptionButtonTP = document.getElementsByClassName('tpButton');
const inscriptionButtonPartner = document.getElementsByClassName('partnerButton');
const inscriptionButtonCustomer = document.getElementsByClassName('customerButton');
const connexionButton = document.getElementById('connexionButton');
const inscriptionSectionCustomer = document.getElementById('leftContainer');
const inscriptionSectionTP = document.getElementById('leftContainerSecond');
const inscriptionSectionPartner = document.getElementById('leftContainerThird');

// Afficher par défaut la section d'inscription pour les clients
inscriptionSectionCustomer.style.display = 'flex';
inscriptionSectionTP.style.display = 'none';
inscriptionSectionPartner.style.display = 'none';

// Gestionnaire d'événements pour les boutons d'inscription
for (const button of inscriptionButtonTP) {
    button.addEventListener('click', function () {
        inscriptionSectionTP.style.display = 'flex';
        inscriptionSectionCustomer.style.display = 'none';
        inscriptionSectionPartner.style.display = 'none';
    });
}

for (const button of inscriptionButtonPartner) {
    button.addEventListener('click', function () {
        inscriptionSectionTP.style.display = 'none';
        inscriptionSectionCustomer.style.display = 'none';
        inscriptionSectionPartner.style.display = 'flex';
    });
}

for (const button of inscriptionButtonCustomer) {
    button.addEventListener('click', function () {
        inscriptionSectionTP.style.display = 'none';
        inscriptionSectionCustomer.style.display = 'flex';
        inscriptionSectionPartner.style.display = 'none';
    });
}

// Création des constantes pour le premier formulaire (Client)
const showPasswordInputCustomer = document.getElementById('passwordInputCustomer');
const showPasswordInputPartner = document.getElementById('passwordInputPartner');
const showPasswordInputTP = document.getElementById('passwordInputTP');
const showPasswordInput = document.getElementById('passwordInput');
const eyeOpenIconCustomer = document.querySelector(".eye-openCustomer");
const eyeClosedIconCustomer = document.querySelector(".eye-closedCustomer");
const eyeOpenIconPartner = document.querySelector(".eye-openPartner");
const eyeClosedIconPartner = document.querySelector(".eye-closedPartner");
const eyeOpenIconTP = document.querySelector(".eye-openTP");
const eyeClosedIconTP = document.querySelector(".eye-closedTP");
const eyeOpenIcon = document.querySelector(".eye-open");
const eyeClosedIcon = document.querySelector(".eye-closed");


eyeOpenIconCustomer.addEventListener("click", () => {
    showPasswordInputCustomer.type = "text";
    eyeOpenIconCustomer.style.display = 'none';
    eyeClosedIconCustomer.style.display = 'block';
});

eyeClosedIconCustomer.addEventListener("click", () => {
    showPasswordInputCustomer.type = "password";
    eyeClosedIconCustomer.style.display = 'none';
    eyeOpenIconCustomer.style.display = 'block';
});

// Création des constantes pour le deuxième formulaire (Travel)

eyeOpenIconTP.addEventListener("click", () => {
    showPasswordInputTP.type = "text";
    eyeOpenIconTP.style.display = 'none';
    eyeClosedIconTP.style.display = 'block';
});

eyeClosedIconTP.addEventListener("click", () => {
    showPasswordInputTP.type = "password";
    eyeClosedIconTP.style.display = 'none';
    eyeOpenIconTP.style.display = 'block';
});

// MÉTHODE FINALE SHOW/HIDE PASSWORD

eyeOpenIcon.addEventListener("click", () => {
    showPasswordInput.type = "text";
    eyeOpenIcon.style.display = 'none';
    eyeClosedIcon.style.display = 'block';
});

eyeClosedIcon.addEventListener("click", () => {
    showPasswordInput.type = "password";
    eyeClosedIcon.style.display = 'none';
    eyeOpenIcon.style.display = 'block';
});

// Création des constantes pour le troisième formulaire (Partenaire)

eyeOpenIconPartner.addEventListener("click", () => {
    showPasswordInputPartner.type = "text";
    eyeOpenIconPartner.style.display = 'none';
    eyeClosedIconPartner.style.display = 'block';
});

eyeClosedIconPartner.addEventListener("click", () => {
    showPasswordInputPartner.type = "password";
    eyeClosedIconPartner.style.display = 'none';
    eyeOpenIconPartner.style.display = 'block';
});
/*------------------------------------Zone validation des entrées input-----------------------------------------*/
