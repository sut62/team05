<template>
  <v-container>
    <v-navigation-drawer
      absolute
      dark
      src="https://c.wallhere.com/photos/e4/53/1920x1080_px_anime_Der_Wanderer_ber_Dem_Nebelmeer_nature_sky_sunset-609196.jpg!d"
      width="100%"
      permanent
    >
    <br />
    <br />
    <br />

    <v-card max-width="800" class="mx-auto">
      <v-system-bar color="pink"></v-system-bar>
      <v-system-bar color="pink"></v-system-bar>
      <v-row justify="center">
        <v-toolbar-title>
          <h1>ยินดีต้อนรับ</h1>
        </v-toolbar-title>
      </v-row>
      <v-col justify="center">
        <v-row justify="center">
          <v-btn style="width:250px;height:60px " color="pink" @click="Members">
            <font color="#FFFFFF">สมัครสมาชิกผู้ใช้งานสถานกีฬา</font>
          </v-btn>
        </v-row>
        <br />

        <v-row justify="center">
          <v-col style="margin-left:10%">
          <v-btn style="width:250px;height:60px" color="pink" @click="Reservation">
            <font color="#FFFFFF">จองเข้าใช้สถานกีฬา</font>
          </v-btn>
          </v-col>
          <v-col style="margin-right-:10%">
          <v-btn style="width:250px;height:60px" color="pink" @click="ReservationData">
            <font color="#FFFFFF">แสดงข้อมูลการจองสถานกีฬา</font>
          </v-btn>
          </v-col>
        </v-row>

        <br />
        <v-row justify="center">
          <v-btn style="width:250px;height:60px" color="pink" @click="Borrow">
            <font color="#FFFFFF">ยืมอุปกรณ์กีฬา</font>
          </v-btn>
        </v-row>
        <br />

        <v-row justify="center">
          <v-col style="margin-left:10%">
          <v-btn style="width:250px;height:60px" color="pink" @click="Returns">
            <font color="#FFFFFF">คืนอุปกรณ์กีฬา</font>
          </v-btn>
          </v-col>
          <v-col style="margin-right-:10%">
          <v-btn style="width:250px;height:60px" color="pink" @click="Returnshistory">
            <font color="#FFFFFF">ประวัติการคืนอุปกรณ์กีฬา</font>
          </v-btn>
          </v-col>
        </v-row>

        <br />
        <v-row justify="center">
          <v-btn style="width:250px;height:60px" color="pink" @click="Sportequitment">
            <font color="#FFFFFF">ลงทะเบียนอุปกรณ์กีฬา</font>
          </v-btn>
        </v-row>
      </v-col>

      <br />
      <br />
      <v-system-bar color="pink"></v-system-bar>
      <v-system-bar color="pink"></v-system-bar>
    </v-card>
    </v-navigation-drawer>
  </v-container>
</template>



<script>
export default {
  methods: {
    Members() {
      this.$router.push("/Employeemenu/Members");
    },
    Reservation() {
      this.$router.push("/Employeemenu/Reservation");
    },
    ReservationData() {
      this.$router.push("/Employeemenu/ReservationData");
    },
    Borrow() {
      this.$router.push("/Employeemenu/Borrow");
    },
    Returns() {
      this.$router.push("/Employeemenu/Returns");
    },
    Returnshistory(){
      this.$router.push("/Employeemenu/Returnhistory");
    },
    Sportequitment() {
      this.$router.push("/Employeemenu/Sportequipment");
    }
  }
};
</script>
