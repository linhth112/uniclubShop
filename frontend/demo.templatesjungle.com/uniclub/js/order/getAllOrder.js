$(document).ready(function () {
  let table = $("#table-order").DataTable({
    ajax: {
      method: "GET",
      url: "http://localhost:8080/order/getAll",
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
      dataSrc: function (json) {
        json.forEach(item => {
          item.status = item.status ? item.status.toLowerCase().trim() : "";
        });
        console.log("Dữ liệu trả về:", json);
        return json;
      },
    },
    columns: [
      { data: "idOder" },
      { data: "fullName" },
      { data: "address" },
      { data: "total" },
      { data: "message" },
      {
        data: "status",
        render: function (data, type, row) {
          return `
            <select class="status-dropdown" id="select-order" data-order-id="${row.idOder}">
              <option value="open" ${data === "open" ? "selected" : ""}>open</option>
              <option value="confirmed" ${data === "confirmed" ? "selected" : ""}>confirmed</option>
              <option value="cancel" ${data === "cancel" ? "selected" : ""}>cancel</option>
            </select>
          `;
        },
      },
      {
        render: function (data, type, row) {
          return `<button class="btn btn-dark btn-details" data-order-id="${row.idOder}">Chi tiết</button>`;
        },
      },
      {
        render: function (data, type, row) {
          return `
            <button class="btn btn-dark btn-confirm" data-order-id="${row.idOder}">Cập nhật</button>
            <button class="btn btn-dark btn-delete" data-order-id="${row.idOder}">Xóa</button>
          `;
        },
      },
    ],
  });

  // Hàm lọc theo trạng thái
  $("#btn-filter").on("click", function () {
    let statusFilter = $("#select-filter").val().toLowerCase().trim(); // Loại bỏ khoảng trắng dư thừa
    console.log("Trạng thái lọc:", statusFilter);

    // Nếu chọn "all", sẽ hiển thị tất cả các dòng
    if (statusFilter === "all") {
      table.rows().every(function() {
        this.node().style.display = ""; // Hiển thị lại tất cả dòng
      });
      table.draw(); // Vẽ lại bảng
    } else {
      // Kiểm tra trạng thái hợp lệ
      if (["open", "confirmed", "cancel"].includes(statusFilter)) {
        // Duyệt qua từng dòng và kiểm tra trạng thái
        table.rows().every(function() {
          let status = this.data().status; // Lấy giá trị trạng thái từ dữ liệu
          // Kiểm tra nếu trạng thái không khớp, ẩn dòng
          if (status !== statusFilter) {
            this.node().style.display = "none"; // Ẩn dòng
          } else {
            this.node().style.display = ""; // Hiển thị dòng
          }
        });
        table.draw(); // Vẽ lại bảng sau khi thay đổi hiển thị dòng
      } else {
        // Nếu trạng thái không hợp lệ, xóa bộ lọc và hiển thị tất cả
        table.rows().every(function() {
          this.node().style.display = ""; // Hiển thị lại tất cả dòng
        });
        table.draw(); // Vẽ lại bảng
      }
    }
  });
});
