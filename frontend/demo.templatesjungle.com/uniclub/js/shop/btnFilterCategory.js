export function setupCategoryClickEvent() {
    $(".product-categories").on("click", "li", function() {
        let idCategory = $(this).attr("id-category");
        console.log(idCategory);
        
    })
}