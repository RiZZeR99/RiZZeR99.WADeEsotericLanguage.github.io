const difficultyOptions = ['easy', 'moderate', 'hard', 'no_chance'];
const releaseYearOptions = generateYearOptions(1950, new Date().getFullYear());
const authorNationalityOptions = ['romanian', 'american', 'british', 'french', 'german', 'italian', 'spanish', 'chinese', 'indian', 'japanese', 'russian', 'canadian', 'australian', 'swedish', 'dutch', 'mexican', 'brazilian', 'argentinian', 'south_african', 'egyptian'];
const booleanOptions = ['Yes', 'No']
extracted_languages = [];


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
populateDropdown('with_examples', booleanOptions, true);

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
            with_examples_required: false
        }
    };
}

function submitCriteria() {
    const selectedDifficulty = document.getElementById("difficulty").value;
    const selectedReleaseYear = document.getElementById("release_year").value;
    const selectedAuthorNationality = document.getElementById("author_nationality").value;
    const selectedWithProgramExamplePreference = document.getElementById("with_examples").value;
    const mappedProgramExamplePreference = selectedWithProgramExamplePreference === 'Yes' ? true : selectedWithProgramExamplePreference === 'No' ? false : null;

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
            required: selectedWithProgramExamplePreference !== '',
            with_examples_required: mappedProgramExamplePreference
        }
    };

    const finalCriteria = Object.assign({}, getDefaultJSON(), userSelectedCriteria);

    sendData(finalCriteria);
}

function mapYesNoToBoolean(stringValue) {
    switch (stringValue) {
        case 'Yes': return true;
        case 'No': return false;
        default: return null;
    }
}

function mapBooleanValueToYeNo(booleanValue) {
    switch (booleanValue) {
        case true: return 'Yes';
        case false: return 'No';
        default: return 'None';
    }
}

function randomizeCriteria() {
    const randomDifficulty = difficultyOptions[Math.floor(Math.random() * difficultyOptions.length)];
    const randomReleaseYear = releaseYearOptions[Math.floor(Math.random() * releaseYearOptions.length)];
    const randomAuthorNationality = authorNationalityOptions[Math.floor(Math.random() * authorNationalityOptions.length)];
    const randomMappedExamples = mapYesNoToBoolean(booleanOptions[Math.floor(Math.random() * booleanOptions.length)]);

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
            required: randomMappedExamples !== null,
            with_examples_required: randomMappedExamples
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
            displayLanguages(data)
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

function updateUI(criteria) {
    document.getElementById('difficulty').value = criteria.complexity.difficulty;
    document.getElementById('release_year').value = criteria.release_year.year;
    document.getElementById('author_nationality').value = criteria.author_details.author_details.nationality;
    document.getElementById('with_examples').value = mapBooleanValueToYeNo(criteria.with_program_examples.with_examples_required);
}

function displayLanguages(data) {
    const responseContainer = document.getElementById('response-container');
    responseContainer.innerHTML = ''; // Clear previous content

    const languages = JSON.parse(data);

    languages.forEach(language => {
        const languageDiv = document.createElement('div');
        languageDiv.classList.add('language-info');

        const nameHeader = document.createElement('h2');
        nameHeader.textContent = language.name;
        languageDiv.appendChild(nameHeader);

        const addButton = document.createElement('button');
        addButton.setAttribute("id", "button-" + language.name);
        addButton.textContent = '+';
        addButton.addEventListener('click', () => getSpecificLanguageInfo(language, languageDiv));
        languageDiv.appendChild(addButton);

        responseContainer.appendChild(languageDiv);
    });
}

function getSpecificLanguageInfo(language, languageDiv) {
    if (extracted_languages[language.name] === undefined) // checking if the language was already extracted from the ontology
    {
        uri = 'http://localhost:8080/getByName' + '/' + escape(extractNameFromUri(language.resource_uri));
        fetch(uri, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            }
        })
            .then(response => response.text())
            .then(data => {
                extracted_languages[language.name] = data; // marking that the language was already called/extracted from the ontology
                showDetails(data, languageDiv)
            })
            .catch(error => {
                console.error('Error:', error);
            });
    }
}

function extractNameFromUri(uri) {
    const index = uri.lastIndexOf('#');
    if (index !== -1) {
        return uri.substring(index + 1);
    }
    return uri; // Return the original URI if no fragment is found
}

function showDetails(data, languageDiv) {
    language = JSON.parse(data);
    const descriptionParagraph = document.createElement('p');
    descriptionParagraph.textContent = language.description;
    languageDiv.appendChild(descriptionParagraph);

    if (language.compilers && language.compilers.length > 0) {
        const compilersHeader = document.createElement('h3');
        compilersHeader.textContent = 'Compilers';
        languageDiv.appendChild(compilersHeader);

        const compilersList = document.createElement('ul');
        language.compilers.forEach(compiler => {
            const compilerItem = document.createElement('li');
            compilerItem.textContent = `${compiler.name}: ${compiler.description}`;
            compilersList.appendChild(compilerItem);
        });
        languageDiv.appendChild(compilersList);
    }

    if (language.program_examples && language.program_examples.length > 0) {
        const examplesHeader = document.createElement('h3');
        examplesHeader.textContent = 'Program Examples';
        languageDiv.appendChild(examplesHeader);

        const examplesList = document.createElement('ul');
        language.program_examples.forEach(example => {
            const exampleItem = document.createElement('li');
            exampleItem.innerHTML = `<strong>Code:</strong> ${example.code}<br><strong>Description:</strong> ${example.description}`;
            examplesList.appendChild(exampleItem);
        });
        languageDiv.appendChild(examplesList);
    }

    if (language.author_details) {
        const authorDetailsHeader = document.createElement('h3');
        authorDetailsHeader.textContent = 'Author Details';
        languageDiv.appendChild(authorDetailsHeader);

        const authorDetailsParagraph = document.createElement('p');
        if (language.author_details.name) {
            authorDetailsParagraph.innerHTML += `<strong>Name:</strong> ${language.author_details.name}<br>`;
        }
        if (language.author_details.nationality) {
            authorDetailsParagraph.innerHTML += `<strong>Nationality:</strong> ${language.author_details.nationality}<br>`;
        }
        if (language.author_details.birthDate) {
            authorDetailsParagraph.innerHTML += `<strong>Birth Date:</strong> ${language.author_details.birthDate}<br>`;
        }
        languageDiv.appendChild(authorDetailsParagraph);
    }

    const externalLinkParagraph = document.createElement('p');

    const linkElement = document.createElement('a');
    linkElement.textContent = "External link";
    linkElement.href = language.external_link;

    externalLinkParagraph.appendChild(linkElement);

    languageDiv.appendChild(externalLinkParagraph);
}