$(document).ready(function () {
  $("#btn-proceedToCheckout").click(function () {
    var currentToken = localStorage.getItem("token");
    if (currentToken === null) {
      const isConfirmed = confirm(
        "You need to login. Do you want to go to login page?"
      );
      if (isConfirmed) {
        window.location.href = "account.html";
      }
    } else {
      const isConfirmed = confirm(
        "Are you sure you want to proceed to checkout?"
      );
      if (isConfirmed) {
        $("#list-cart tr").each(function () {
          let idProductRq = $(this).attr("id-product");
          let idSizeRq = $(this).attr("id-size");
          let idColorRq = $(this).attr("id-color");

          let quantityRq = $(this).find("#input-quantity").val();
          //   console.log(quantity);

          $.ajax({
            method: "POST",
            url: "http://localhost:8080/cart/update",
            headers: {
              Authorization: "Bearer " + localStorage.getItem("token").trim(),
              "Content-Type": "application/json",
            },
            data: JSON.stringify({
              idProduct: idProductRq,
              idSize: idSizeRq,
              idColor: idColorRq,
              quantity: quantityRq,
            }),
          }).done(function (result) {
            console.log(result);
          });

          window.location.href = "checkout.html";
        });
      }
    }
  });
});
