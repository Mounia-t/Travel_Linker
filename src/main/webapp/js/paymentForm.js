document.addEventListener('DOMContentLoaded', function() {
	// Récupération des éléments HTML
	const nomInput = document.querySelector('[id$="nom"]');
	const cbNumberSpan = document.querySelector('[id$="cbNumber"]');
	const ownerSpan = document.querySelector('[id$="owner"]');
	const numeroCarteInput = document.querySelector('[id$="numeroCarte"]');
	const cvvInput = document.querySelector('[id$="cvv"]');
	const cvvValueSpan = document.querySelector('[id$="cvvValue"]');
	const expirationDateInput = document.querySelector('[id$="expirationDate"]');
	const cbDateSpan = document.querySelector('[id$="cbDate"]');
	const cbLogoSpan = document.querySelector('[id$="cbLogo"]');


	// Écouteur d'événement pour le champ de texte nom complet
	nomInput.addEventListener('input', function() {
		ownerSpan.textContent = nomInput.value ? nomInput.value : '****';
	});

	// Écouteur d'événement pour le champ de texte numéro de carte
	numeroCarteInput.addEventListener('input', function() {
		cbNumberSpan.textContent = numeroCarteInput.value ? formatCardNumber(numeroCarteInput.value) : '**** **** **** ****';
	});

	// Écouteur d'événement pour le champ de texte de la date d'expiration
	expirationDateInput.addEventListener('input', function() {
		cbDateSpan.textContent = expirationDateInput.value ? formatExpirationDate(expirationDateInput.value) : '09/23';
	});

	// Écouteur d'événement pour le champ de texte CVV
	cvvInput.addEventListener('input', function() {
		cvvValueSpan.textContent = cvvInput.value ? cvvInput.value : '***';
	});

	// Fonction pour formater le numéro de carte (ajoute des espaces chaque 4 caractères)
	function formatCardNumber(cardNumber) {
		const formattedNumber = cardNumber.replace(/\s/g, '').replace(/(\d{4})(?=\d)/g, '$1 ');
		return formattedNumber;
	}

	// Fonction pour formater la date d'expiration (ajoute un slash après les deux premiers caractères)
	function formatExpirationDate(date) {
		const formattedDate = date.replace(/\s/g, '').replace(/(\d{2})(\d{2})/, '$1/$2');
		return formattedDate;
	}

	// Écouteur d'événement pour le champ de texte numéro de carte
	numeroCarteInput.addEventListener('input', function() {
		const cardNumber = numeroCarteInput.value;
		updateCardLogo(cardNumber);
	});

	// Fonction pour mettre à jour le logo de la carte en fonction du numéro de carte
	function updateCardLogo(cardNumber) {
		let cardLogo = '';

		if (/^4/.test(cardNumber)) {
			cardLogo = '<img src="./media/images/visa.svg">';
		} else if (/^5/.test(cardNumber)) {
			cardLogo = '<img src="./media/images/mastercard-2.svg" style="height: 50px">';
		} else if (/^3[47]/.test(cardNumber)) {
			cardLogo = '<img src="./media/images/amex.svg">';
		}

		cbLogoSpan.innerHTML = cardLogo;
	}
});
