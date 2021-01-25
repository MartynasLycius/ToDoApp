import Swal from "sweetalert2";

export default {
    showLoading() {
        Swal.showLoading()
    },
    hideLoading() {
        Swal.close()
    },
    showFailed(message) {
      Swal.fire('Failed!', message, 'error');
    },
    getConfirmation() {
        return Swal.fire({
            icon: 'warning',
            iconColor: '#b3dc10',
            titleText: "Your Todo Item done !",
            text: "You are about finished this task",
            reverseButtons: true,
            showDenyButton: true,
            showConfirmButton: true,
            confirmButtonText: 'Yes'
        }).then(({value}) => !!value)
    },
    showSuccessMessage(message) {
      Swal.fire('Successful!', message, 'success');
    },
}
