$(document).ready(function () {
  $("#list-cart").on("click", "#left-button", function () {
    // console.log($(this));
    let dataId = $(this).data("id");
    let currentQuantity = parseInt($(`input[data-id="${dataId}"]`).val());
    if (currentQuantity > 1) {
      let futureQuantity = currentQuantity - 1;
      $(`input[data-id="${dataId}"]`).val(futureQuantity);
      // console.log(futureQuantity);

      let price = parseFloat($(`tr[data-id="${dataId}"]`).attr("price"));
      // console.log(price);

      let newTotalPrice = parseFloat(futureQuantity) * price;
      // console.log(newTotalPrice);

      $(`tr[data-id="${dataId}"] #total-price`).text(
        `$ ${newTotalPrice.toFixed(2)}`
      );
    }
  });
  $("#list-cart").on("click", "#right-button", function () {
    let dataId = $(this).data("id");
    let currentQuantity = parseInt($(`input[data-id="${dataId}"]`).val());
    let futureQuantity = currentQuantity + 1;
    $(`input[data-id="${dataId}"]`).val(futureQuantity);
    let price = parseFloat($(`tr[data-id="${dataId}"]`).attr("price"));
    // console.log(price);

    let newTotalPrice = parseFloat(futureQuantity) * price;
    // console.log(newTotalPrice);

    $(`tr[data-id="${dataId}"] #total-price`).text(
      `$ ${newTotalPrice.toFixed(2)}`
    );
  });
});
