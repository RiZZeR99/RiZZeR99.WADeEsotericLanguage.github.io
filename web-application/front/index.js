const difficultyOptions = ['easy', 'moderate', 'hard', 'no_chance'];
const releaseYearOptions = generateYearOptions(1950, new Date().getFullYear());
const authorNationalityOptions = ['romanian', 'american', 'british', 'french', 'german', 'italian', 'spanish', 'chinese', 'indian', 'japanese', 'russian', 'canadian', 'australian', 'swedish', 'dutch', 'mexican', 'brazilian', 'argentinian', 'south_african', 'egyptian'];

function generateYearOptions(startYear, endYear) {
    const options = [];
    for (let year = endYear; year >= startYear; year--) {
        options.push(year.toString());
    }
    return options;
}

function populateDropdown(elementId, options, allowNone = false) {
    const dropdown = document.getElementById(elementId);

    if (allowNone) {
        const noneOption = document.createElement('option');
        noneOption.value = '';
        noneOption.text = 'None';
        dropdown.add(noneOption);
    }

    options.forEach(option => {
        const optionElement = document.createElement('option');
        optionElement.value = option;
        optionElement.text = option;
        dropdown.add(optionElement);
    });
}

// Populate release year and author nationality dropdowns with "None" option
populateDropdown('release_year', releaseYearOptions, true);
populateDropdown('author_nationality', authorNationalityOptions, true);

function getDefaultJSON() {
    return {
        complexity: {
            difficulty: '',
            required: false
        },
        release_year: {
            required: false,
            year: ''
        },
        author_details: {
            required: false,
            author_details: {
                name: '',
                nationality: '',
                birthDate: ''
            }
        },
        with_program_examples: {
            required: false,
            with_examples_required: true
        }
    };
}

function submitCriteria() {
    const selectedDifficulty = document.getElementById("difficulty").value;
    const selectedReleaseYear = document.getElementById("release_year").value;
    const selectedAuthorNationality = document.getElementById("author_nationality").value;

    const userSelectedCriteria = {
        complexity: {
            difficulty: selectedDifficulty,
            required: selectedDifficulty !== '' // Set required to true if a value is selected
        },
        release_year: {
            required: selectedReleaseYear !== '', // Set required to true if a value is selected
            year: selectedReleaseYear
        },
        author_details: {
            required: selectedAuthorNationality !== '', // Set required to true if a value is selected
            author_details: {
                name: '',
                nationality: selectedAuthorNationality,
                birthDate: ''
            }
        },
        with_program_examples: {
            required: true,
            with_examples_required: true
        }
    };

    const finalCriteria = Object.assign({}, getDefaultJSON(), userSelectedCriteria);

    sendData(finalCriteria);
}

function randomizeCriteria() {
    const randomDifficulty = difficultyOptions[Math.floor(Math.random() * difficultyOptions.length)];
    const randomReleaseYear = releaseYearOptions[Math.floor(Math.random() * releaseYearOptions.length)];
    const randomAuthorNationality = authorNationalityOptions[Math.floor(Math.random() * authorNationalityOptions.length)];

    const randomUserSelectedCriteria = {
        complexity: {
            difficulty: randomDifficulty,
            required: randomDifficulty !== '' // Set required to true if a value is selected
        },
        release_year: {
            required: randomReleaseYear !== '', // Set required to true if a value is selected
            year: randomReleaseYear
        },
        author_details: {
            required: randomAuthorNationality !== '', // Set required to true if a value is selected
            author_details: {
                name: '',
                nationality: randomAuthorNationality,
                birthDate: ''
            }
        },
        with_program_examples: {
            required: true,
            with_examples_required: true
        }
    };

    const finalCriteria = Object.assign({}, getDefaultJSON(), randomUserSelectedCriteria);

    updateUI(finalCriteria);
}

function sendData(criteria) {
    fetch('http://localhost:8080/getByCriteria', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(criteria),
    })
    .then(response => response.text())
    .then(data => {
        document.getElementById('response').value = data;
    })
    .catch(error => {
        console.error('Error:', error);
    });
}

function updateUI(criteria) {
    document.getElementById('difficulty').value = criteria.complexity.difficulty;
    document.getElementById('release_year').value = criteria.release_year.year;
    document.getElementById('author_nationality').value = criteria.author_details.author_details.nationality;
}
