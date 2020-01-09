<template>
  <v-container>
    <br />
    <br />
    <br />
    <br />

    <v-card max-width="700" class="mx-auto">
      <v-system-bar color="green"></v-system-bar>
      <v-system-bar color="green"></v-system-bar>
      <v-row justify="center">
        <v-toolbar-title>
          <h1>ยินดีต้อนรับ</h1>
        </v-toolbar-title>
      </v-row>
      <v-row justify="center">
        <v-col>
          <v-row justify="center">
            <v-btn style="width:180px;height:60px " color="green" @click="aa">
              <font color="#FFFFFF">สมัครพนักงาน</font>
            </v-btn>
          </v-row>
        </v-col>
        <!-- <v-col>
          <v-row justify="center">
            <v-btn style="width:180px;height:60px" color="pink" @click="bb">
              <font color="#FFFFFF">นัดสัมภาษณ์งาน</font>
            </v-btn>
          </v-row>
        </v-col>-->
      </v-row>

      <br />
      <br />
      <v-system-bar color="green"></v-system-bar>
      <v-system-bar color="green"></v-system-bar>
    </v-card>
  </v-container>
</template>



<script>
export default {
  methods: {
    aa() {
      this.$router.push("/Adminmenu/Employee");
    },
    // bb() {
    //   this.$router.push("/registercompany/login/Interview");
    // }
  }
};
</script>
