
<template>
  <v-card
    class="mx-auto"
    height="100%"
    width="100%"
  >
    <v-navigation-drawer
      absolute
      dark
      src="https://c.wallhere.com/photos/e4/53/1920x1080_px_anime_Der_Wanderer_ber_Dem_Nebelmeer_nature_sky_sunset-609196.jpg!d"
      width="100%"
      permanent
    >

    <br>
<v-card
    class="mx-auto"
    max-width="850"
    color = "#8B636C"
  >
  
<v-system-bar color=#CD919E></v-system-bar>
<v-system-bar color=#CD919E></v-system-bar>

 <v-row justify="center">
<v-toolbar-title ><h1>สมัครสมาชิก</h1></v-toolbar-title>

</v-row>
<v-row justify="center">
  <v-col cols="2">
                <v-select
                  label="คำนำหน้า"
                  solo
                   v-model="Members.nametypeId"
                  :items="nametypes"
                  item-text="nametype"
                  item-value="id"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-select>
              </v-col>

  <v-col cols="12" sm="6" md="5" >
          <v-text-field
            label="ชื่อ - นามสกุล"
            solo

            v-model= "name"
                :rules="[(v) => !!v || 'This field is required']"
                required
                clearable

          ></v-text-field>
        </v-col>
</v-row>
<v-row justify="center">
<v-col cols="3">
                <v-select
                  label="เพศ"
                  solo
                  v-model="Members.genderId"
                  :items="genders"
                  item-text="gender"
                  item-value="id"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-select>
              </v-col>
              
<v-col cols="12" sm="6" md="4">
      <v-menu
        ref="menu"
        v-model="menu"
        :close-on-content-click="false"
        :return-value.sync="date"
        transition="scale-transition"
        offset-y
        min-width="290px"
      >
        <template v-slot:activator="{ on }">
          <v-text-field
            v-model="date"
            label="วันเดือนปีเกิด"
            prepend-icon="mdi-calendar"
            readonly
            v-on="on"
          ></v-text-field>
        </template>
        <v-date-picker v-model="date" no-title scrollable>
          <div class="flex-grow-1"></div>
          <v-btn text color="primary" @click="menu = false">Cancel</v-btn>
          <v-btn text color="primary" @click="$refs.menu.save(date)">OK</v-btn>
        </v-date-picker>
      </v-menu>
    </v-col>
              
</v-row>
<v-row justify="center">
   <v-col cols="12" sm="6" md="4" >
          <v-text-field
            label="ที่อยู่"
            solo
            v-model= "address"
            :rules="[(v) => !!v || 'This field is required']"
            required
            clearable
          ></v-text-field>
        </v-col>

         <v-col cols="3">
                <v-select
                  label="จังหวัด"
                  solo
                   v-model="Members.provinceId"
                  :items="provinces"
                  item-text="province"
                  item-value="id"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-select>
              </v-col>
</v-row>


<v-row justify="center">
        <v-col cols="12" sm="6" md="7" >
          <v-text-field
            label="อีเมล"
            prepend-icon="mdi-email"
            solo
            v-model= "email"
            :rules="[(v) => !!v || 'This field is required']"
            required
            clearable
          ></v-text-field>
        </v-col>
</v-row>

<v-row justify="center">
        <v-col cols="12" sm="6" md="7" >
          <v-text-field
            label="เบอร์โทรศัพท์"
            prepend-icon="mdi-cellphone"
            solo
            v-model= "phonenumber"
            :rules="[(v) => !!v || 'This field is required']"
            required
            clearable
          ></v-text-field>
        </v-col>
</v-row>
 
<v-row justify="center">
            <v-col cols="12">
              <v-btn 
                style="margin-left: 25%;"
                @click="clear">clear
              </v-btn>
              <v-btn 
                style="margin-left: 30%;"
                @click="saveData">save 
              </v-btn>
            </v-col>
          </v-row>
<br>

<v-system-bar color=#CD919E></v-system-bar>
<v-system-bar color=#CD919E></v-system-bar>
</v-card>
</v-navigation-drawer>
</v-card>

</template>

<script>
import http from "../http-common";
export default {
  name: "Members",
  data() {
    return {
      Members: {
        genderId: null,
        nametypeId: null,
        provinceId: null,
      },
      name: "",
      date: "",
      address: "",
      email: "",
      phonenumber: "",
      genders: null,
      nametypes: null,
      provinces: null
      
    };
  },

 methods: {
  
  // ดึงข้อมูล NameType ใส่ combobox
    getGender() {
      http
        .get("/gender")
        .then(response => {
          this.genders = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
        // ดึงข้อมูล PhoneType ใส่ combobox
    getNametype() {
      http
        .get("/nametype")
        .then(response => {
          this.nametypes = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
  getProvince() {
      http
        .get("/province")
        .then(response => {
          this.provinces = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
  
    // function เมื่อกดปุ่ม submit
   saveData() {
      http
        .post(
          "/Members/" + this.name +
           "/" + this.date + 
           "/" + this.address + 
           "/" + this.email+
            "/" + this.phonenumber  + 
             "/" +  this.Members.nametypeId + 
              "/" +  this.Members.genderId + 
            "/" + this.Members.provinceId ,
            this.Members
          
        )
    .then(response => {
        
          console.log(response);
          alert("บันทึกสำเร็จ");  
        })
    .catch(e => {
          console.log(e);
          alert("บันทึกไม่สำเร็จ");  
        });  
    
     
  },
    clear() {
     
        this.name = '';
        this.date = '';
        this.address = '';
        this.email = '';
        this.phonenumber = '';
       
        this.Members.nametypes = null;
        this.Members.genders = null;
        this.Members.provinces = null;
    },

  },
    mounted() {
    
      this.getGender();
      this.getNametype();
      this.getProvince();
      
  }
};
</script>
