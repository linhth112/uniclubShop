$(document).ready(function () {
  $("#btn-insert-order").click(function (event) {
    event.preventDefault();
    var currentToken = localStorage.getItem("token");
    if (currentToken === null) {
      const isConfirmed = confirm(
        "You need to login. Do you want to go to login page?"
      );
      if (isConfirmed) {
        console.log("ok");

        window.location.href = "account.html";
      }
    } else {
      let fullNameIp = $("#fname").val();
      let addressIp = $("#adr").val();
      let phoneIp = $("#phone").val();
      let messageIp = $("#message").val();
      let totalIp = parseFloat($("#cart-total-checkout").text().substring(2));

      const regex = /^(0[3|5|7|8|9])+([0-9]{8})$/;

      if (fullNameIp.trim() === "") {
        alert("You have not entered a full name!");
      } else if (addressIp.trim() === "") {
        alert("You have not entered a address!");
      } else if (phoneIp.trim() === "") {
        alert("You have not entered a phone!");
      } else if (!regex.test(phoneIp)) {
        alert("Invalid phone number");
      } else {
        const isConfirmed = confirm(
          "Are you sure you want to send this order?"
        );

        if (isConfirmed) {
          $.ajax({
            method: "POST",
            url: "http://localhost:8080/order",
            headers: {
              Authorization: "Bearer " + localStorage.getItem("token").trim(),
              "Content-Type": "application/json",
            },
            data: JSON.stringify({
              fullName: fullNameIp,
              address: addressIp,
              total: totalIp,
              phone: phoneIp,
              message: messageIp,
            }),
          }).done(function (result) {
            console.log(result);
            if (result.data === true) {
              alert("Order sended successfully!");
            } else {
              alert("Order sended fail!");
            }
          });
        }
      }
    }
  });
});
