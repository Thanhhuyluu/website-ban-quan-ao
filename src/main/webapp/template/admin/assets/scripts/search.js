function searchOnEnter(event) {
    // Check if Enter key is pressed
    if (event.key === 'Enter') {
        event.preventDefault(); // Prevent the default form submission

        // Submit the form
        document.getElementById('searchForm').submit();
    }
}

// Ensure the DOM is fully loaded before attaching event listeners
document.addEventListener('DOMContentLoaded', function() {
    // Get the search input element
    const searchInput = document.querySelector('.header__search-input');
    if (searchInput) {
        // Attach the keydown event listener to the search input
        searchInput.addEventListener('keydown', searchOnEnter);
    }
});