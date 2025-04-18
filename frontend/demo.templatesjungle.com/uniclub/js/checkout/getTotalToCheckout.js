$(document).ready(function () {
  let total = 0;

  $.ajax({
    method: "GET",
    url: "http://localhost:8080/cart",
    headers: {
      Authorization: "Bearer " + localStorage.getItem("token"),
    },
  }).done(function (result) {
    if (result.data) {
      for (i = 0; i < result.data.length; i++) {
        let price = result.data[i].price;
        let quantity = result.data[i].quantity;
        total += price * quantity;
      }
    }
    $("#cart-total-checkout").text(`$ ${total}`);
  });
});
