$(document).ready(function () {
  $.ajax({
    method: "GET",
    url: "http://localhost:8080/cart",
    headers: {
      Authorization: "Bearer " + localStorage.getItem("token"),
    },
  }).done(function (result) {
    console.log(result);
    var cartTotal = 0;
    var html = "";
    if (result.data) {
      for (i = 0; i < result.data.length; i++) {
        var item = result.data[i];

        html += `<tr data-id="${item.idCart}" price="${
          item.price
        }" id-product="${item.idProduct}" id-size="${item.idSize}" id-color="${
          item.idColor
        }">
                <td scope="row" class="py-4">
                  <div class="cart-info d-flex flex-wrap align-items-center ">
                    <div class="col-lg-2">
                      <div class="card-image">
                        <img src="${
                          item.images[0]
                        }" alt="cloth" class="img-fluid">
                      </div>
                    </div>
                    <div class="col-lg-9">
                      <div class="card-detail ps-3">
                        <h5 class="card-title">
                          <a href="#" class="text-decoration-none">${
                            item.name
                          }</a>
                        </h5>
                      </div>
                    </div>
                  </div>
                </td>

                <td class="align-middle">
                <div class="ps-3">
                ${item.colorName}/${item.sizeName}
                </div>
                </td>

                <td class="py-4 align-middle pe-3">
                  <div class="input-group product-qty align-items-center w-100">
                    <span class="input-group-btn">
                      <button type="button" data-id="${
                        item.idCart
                      }" id="left-button" class="quantity-left-minus p-1 btn btn-light btn-number" data-type="minus">
                        <svg width="16" height="16">
                          <use xlink:href="#minus"></use>
                        </svg>
                      </button>
                    </span>
                    <input type="text" id="input-quantity" name="quantity"
                      class="form-control input-number text-center p-2 mx-2"  data-id="${
                        item.idCart
                      }" value="${item.quantity}">
                    <span class="input-group-btn">
                      <button type="button" id="right-button" data-id="${
                        item.idCart
                      }" class="quantity-right-plus p-1 btn btn-light btn-number" data-type="plus"
                        data-field="">
                        <svg width="16" height="16">
                          <use xlink:href="#plus"></use>
                        </svg>
                      </button>
                    </span>
                  </div>
                </td>
                <td class="py-4 align-middle ps-3">
                  <div class="total-price">
                    <span id="total-price" class="secondary-font fw-medium">$ ${
                      item.price * item.quantity
                    }</span>
                  </div>
                </td>
                <td class="py-4 align-middle">
                  <div class="cart-remove" id="cart-remove">
                  
                    <a href="#">
                      <svg width="24" height="24">
                        <use xlink:href="#trash"></use>
                      </svg>
                    </a>
                  </div>
                </td>
              </tr>`;
        var total = item.price * item.quantity;
        cartTotal += total;
      }
      htmlTextTotal = `<span id="cart-total" class="price-currency-symbol">$ ${cartTotal}</span>`;
      $("#list-cart").append(html);
      $("#text-cart-total").append(htmlTextTotal);
    }
  });
});
