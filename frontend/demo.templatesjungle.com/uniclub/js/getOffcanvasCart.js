$(document).ready(function () {
  $("#btn-offcanvas").click(function () {
    console.log("oke click");

    $.ajax({
      method: "GET",
      url: "http://localhost:8080/cart",
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    })
      .done(function (result) {
        console.log(result);
        var cartNumber = result.data.length;
        var total = 0;
        var htmlLi = "";

        for (let i = 0; i < result.data.length; i++) {
          var item = result.data[i];
          var sizeName = "";
          var colorName = "";
          var subTotal = item.price * item.quantity;
          switch (item.idColor) {
            case 1:
              colorName = "GRAY";
              break;

            case 2:
              colorName = "BLACK";
              break;

            case 3:
              colorName = "BLUE";
              break;

            default:
              colorName = "RED";
              break;
          }

          switch (item.idSize) {
            case 1:
              sizeName = "S";
              break;

            case 2:
              sizeName = "M";
              break;

            case 3:
              sizeName = "L";
              break;

            default:
              sizeName = "XL";
              break;
          }
          htmlLi += `<li class="list-group-item d-flex justify-content-between lh-sm">
                            <div>
                            <h6 class="my-0">${item.name}</h6>
                            <small class="text-body-secondary">${colorName}/${sizeName}</small>
                            </div>
                            <span class="">(${item.quantity})</span>
                            <span class="text-body-secondary">$${subTotal}</span>
                            </li>`;
          total += subTotal;
        }

        var html = `<div class="order-md-last">
                        <h4 class="d-flex justify-content-between align-items-center mb-4">
                        <span class="text-primary">Your cart</span>
                        <span class="badge bg-primary rounded-circle pt-2">${cartNumber}</span>
                        </h4>

                        <ul class="list-group mb-4">
                        ${htmlLi}
                        <li class="list-group-item d-flex justify-content-between">
                            <span class="fw-bold">Total (USD)</span>
                            <strong>$${total}</strong>
                        </li>
                        </ul>

                        <button id="btn-toCheckout" class="w-100 btn btn-dark" type="submit">Continue to checkout</button>
                    </div>`;

        $("#offcanvas-body").append(html);
        $("#btn-toCheckout").click(function () {
          window.location.href = "checkout.html";
        });
      })
      .fail(function (jqXHR, textStatus, errorThrown) {
        // Xử lý lỗi 403
        if (jqXHR.status === 403) {
          // Thêm nút đăng nhập vào #offcanvas-body
          const loginButton = `<div class="d-flex justify-content-center align-items-center" style="height: 100vh;">
    <a href="account.html" class="btn btn-primary">Login</a>
  </div>`;
          $("#offcanvas-body").append(loginButton);
        } else {
          console.error("Error:", textStatus, errorThrown);
        }
      });
  });

  $("#offcanvasCart").on("hidden.bs.offcanvas", function () {
    $("#offcanvas-body").empty();
  });
});
