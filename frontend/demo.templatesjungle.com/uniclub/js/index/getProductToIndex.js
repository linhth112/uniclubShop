$(document).ready(function () {
  $.ajax({
    method: "GET",
    url: "http://localhost:8080/product",
  }).done(function (result) {
    console.log(result);

    if (result.length > 0) {
      var html = "";
      for (var i = 0; i < result.length; i++) {
        html += `<div class="col-product col-md-6 col-lg-3 my-4">
        <div class="product-item">
            <div class="image-holder" style="width: 100%; height: 100%;">
              <img src="${
                result[i].images.length > 0 ? result[i].images[0] : ""
              }" alt="Books" class="product-image img-fluid">
            </div>
            <div class="w-50 cart-concern">
              <div class="cart-button d-flex justify-content-between align-items-center">
                <a href="single-product.html?id=${
                  result[i].idProduct
                }" class="view-btn">
                  <i class="icon icon-screen-full"></i>
                </a>
                <a href="#" class="wishlist-btn">
                  <i class="icon icon-heart"></i>
                </a>
              </div>
            </div>
            <div class="product-detail d-flex justify-content-between align-items-center mt-4">
              <h4 class="product-title mb-0">
                <a href="single-product.html">${result[i].name}</a>
              </h4>
              <p class="m-0 fs-5 fw-normal">${result[i].price}</p>
            </div>
          </div>
          </div>`;
      }
      $("#product-content").append(html);
    }
  });
});
