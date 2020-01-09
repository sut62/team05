<template>
  <v-container>
    <br />
    <br />
    <br />
    <v-card max-width="700" class="mx-auto">
      <v-system-bar color="pink"></v-system-bar>
      <v-system-bar color="pink"></v-system-bar>
      <v-row justify="center">
        <v-toolbar-title>
          <h1>Login</h1>
        </v-toolbar-title>
      </v-row>
      <v-row justify="center">
        <v-col cols="5">
          <v-text-field
            solo
            label="Email"
            v-model="email"
            :rules="[(v) => !!v || 'This field is required']"
            required
            clearable
            prepend-icon="mdi-email"
          ></v-text-field>
        </v-col>
      </v-row>

      <v-row justify="center">
        <v-col cols="5">
          <v-text-field
            solo
            label="PASSWORD"
            v-model="password"
            :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
            :type="show1 ? 'text' : 'password'"
            prepend-icon="mdi-lock"
            required
            counter
            clearable
            @click:append="show1 = !show1"
          ></v-text-field>
        </v-col>
      </v-row>

      <v-row justify="center">
        <v-col>
          <v-btn style="margin-left:43%;" @click="BLogin">Login</v-btn>

          <!-- <v-btn 
                style="margin-left:25%;"
                @click="BRegister">Register
                 
          </v-btn>-->
        </v-col>
      </v-row>
      <br />
      <br />
      <v-system-bar color="pink"></v-system-bar>
      <v-system-bar color="pink"></v-system-bar>
    </v-card>
  </v-container>
</template>



<script>
import http from "../http-common";

export default {
  name: "Loing",
  data() {
    return {
      email: "",
      password: "",
      show1: false
    };
  },
  methods: {
    BLogin() {
      http
        .get("/checkemployee/" + this.email + "/" + this.password)
        .then(response => {
          console.log(response);
          if (response.data[0] != null) {
            localStorage.setItem("emp_id", response.data[0].emp_id);
            localStorage.setItem("name", response.data[0].name);
            alert("เข้าสู้ระบบสำเร็จ");
            this.$router.push("/Employeemenu");
          } else {
            alert("รหัสผ่านไม่ถูกต้อง");
          }
        })
        .catch(e => {
          console.log(e);
        });
      this.submitted = true;
    },
    refreshList() {}
    //   BRegister(){
    // this.$router.push("/registercompany");
    //   }
  },
  mounted() {}
};
</script>