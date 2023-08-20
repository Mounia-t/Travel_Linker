window.addEventListener('load', function () {
    var rightContainer = document.querySelector('#rightContainer');
    var leftContainer = document.querySelector('#leftContainer');
    rightContainer.style.animationDelay = '0.2s';
    rightContainer.classList.add('fade-in');
    leftContainer.classList.add('fade-in');
});

// Création des constantes
const inscriptionButtonTP = document.querySelectorAll('.tpButton');
const inscriptionButtonPartner = document.querySelectorAll('.partnerButton');
const inscriptionButtonCustomer = document.querySelectorAll('.customerButton');
const connexionButton = document.querySelector('[id$="connexionButton"]');
const inscriptionSectionCustomer = document.querySelector('[id$="leftContainer"]');
const inscriptionSectionTP = document.querySelector('[id$="leftContainerSecond"]');
const inscriptionSectionPartner = document.querySelector('[id$="leftContainerThird"]');


// Afficher par défaut la section d'inscription pour les clients
setDisplay('customer');

// Gestionnaire d'événements pour les boutons d'inscription
function setButtonListeners(buttons, sectionType) {
    for (const button of buttons) {
        button.addEventListener('click', function() {
            setDisplay(sectionType);
        });
    }
}

setButtonListeners(inscriptionButtonTP, 'tp');
setButtonListeners(inscriptionButtonPartner, 'partner');
setButtonListeners(inscriptionButtonCustomer, 'customer');

function setDisplay(type) {
    switch (type) {
        case 'tp':
            inscriptionSectionTP.style.display = 'flex';
            inscriptionSectionCustomer.style.display = 'none';
            inscriptionSectionPartner.style.display = 'none';
            break;
        case 'partner':
            inscriptionSectionTP.style.display = 'none';
            inscriptionSectionCustomer.style.display = 'none';
            inscriptionSectionPartner.style.display = 'flex';
            break;
        case 'customer':
        default:
            inscriptionSectionTP.style.display = 'none';
            inscriptionSectionCustomer.style.display = 'flex';
            inscriptionSectionPartner.style.display = 'none';
            break;
    }
}

// Création des constantes pour le premier formulaire (Client)
const showPasswordInputCustomer = document.querySelector('[id$="passwordInputCustomer"]');
const showPasswordInputPartner = document.querySelector('[id$="passwordInputPartner"]');
const showPasswordInputTP = document.querySelector('[id$="passwordInputTP"]');
const showPasswordInput = document.querySelector('[id$="passwordInput"]');
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
