<?php
session_start();
$id_petugas = $_SESSION['id_petugas'];
$isLoggedIn = $_SESSION['isLoggedIn'];
 
if($isLoggedIn != '1'){
    session_destroy();
    header('Location: login.php');
}
?>
 
Selamat Datang <?php echo $id_petugas; ;?>
<a href="logout.php">Logout</a>;
?>