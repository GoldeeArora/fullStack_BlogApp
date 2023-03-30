import { StyleSheet } from 'react-native';
const styles = StyleSheet.create({
    searchBarContainer:{
        flexDirection: 'row',
        alignItems:"center",
        justifyContent: 'center',
        marginTop: "4%"
   },
   input:{
     width: "80%",
     height: 50,
     borderWidth: 1.3,
     borderRadius: 5,
     margin: 5,
     marginRight: 10,
   },
   iconContainer:{
       borderRadius: 5,
       borderWidth: 1.3,
       width: "13%",
       alignItems: 'center',
       justifyContent: 'center',
       height: 50,
   }
})

export default styles;