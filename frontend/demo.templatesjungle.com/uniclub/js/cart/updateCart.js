$(document).ready(function () {
  $("#btnUpdateCart").click(function () {
    $("#list-cart tr").each(function () {
      let idProductRq = $(this).attr("id-product");
      let idSizeRq = $(this).attr("id-size");
      let idColorRq = $(this).attr("id-color");

      let quantityRq = $(this).find("#input-quantity").val();
      //   console.log(quantity);
      const isConfirmed = confirm("Are you sure you want to update this cart?");

      if (isConfirmed) {
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
          if (result.data) {
            alert("Cart updated successfully!");
          } else {
            alert("Cart updated fail!");
          }
        });
      }
    });
  });
});
