
$(document).ready(function() {
    $.ajax({
        method: "GET",
        url: "http://localhost:8080/product",
      }).done(function (result) {
        console.log(result);
        
        if (result.length > 0) {
            let category = "";
            let htmlProduct = "";
            let htmlCategory = "";
            for (var i = 0; i < result.length; i++) {
              htmlProduct += `
                <div class="col-product col-md-6 col-lg-4 my-4" id-category="${result[i].idCategory}">
                  <div class="product-item">
                    <div class="image-holder" style="width: 100%; height: 100%;">
                      <img src="${result[i].images[0]}" alt="Books" class="product-image img-fluid">
                    </div>
                    <div class="cart-concern">
                      <div class="cart-button d-flex justify-content-between align-items-center">
                        <a href="single-product.html?id=${result[i].idProduct}" class="view-btn">
                          <i class="icon icon-screen-full"></i>
                        </a>
                        <a href="#" class="wishlist-btn">
                          <i class="icon icon-heart"></i>
                        </a>
                      </div>
                    </div>
                    <div class="product-detail d-flex justify-content-between align-items-center mt-4">
                      <h4 class="product-title mb-0">
                        <a>${result[i].name}</a>
                      </h4>
                      <p class="m-0 fs-5 fw-normal">$ ${result[i].price}</p>
                    </div>
                  </div>
                </div>`;

              if(result[i].category != category) {
                category = result[i].category;
                htmlCategory += `<li class="cat-item" >
                                    <a href="#" class="nav-link fw-semibold" id-category="${result[i].idCategory}">${category}</a>
                                </li>`
              }
            }
            $("body .product-store").append(htmlProduct);
            $(".product-categories").append(htmlCategory);
        }

        $(".product-categories").on("click", "li a", function() {
          let idCategory = $(this).attr("id-category");
          console.log(idCategory);
          $("body .product-store .col-product").each(function() { 
            var productCategory = $(this).attr("id-category"); 

            if (productCategory === idCategory || idCategory === "0") {
                $(this).show(); 
            } else {
                $(this).hide(); 
            }
        });
        })
      })
})