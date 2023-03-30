import { StyleSheet } from 'react-native';
// import {  } from 'react-native/Libraries/NewAppScreen';
import {COLORS} from "../../styles/colors"
const styles = StyleSheet.create({
    container: {
        flex: 1,
        alignItems: 'center',
        justifyContent: 'space-around',
        backgroundColor: '#fff',
      },
      loginContainer:{
       flex:1,
       alignItems: 'center',
       justifyContent: 'center',
       width: "100%"
      },
      title: {
        fontSize: 24,
        fontWeight: 'bold',
        marginBottom: 30,
      },
      input: {
        borderWidth: 1,
        borderColor: '#ccc',
        padding: 10,
        borderRadius: 5,
        marginBottom: 20,
        width: '80%',
      },
      button: {
        backgroundColor: COLORS.lightred,
        padding: 10,
        borderRadius: 5,
      },
      buttonText: {
        color: '#fff',
        fontSize: 18,
      },
signUpContainer:{
  flexDirection: 'row',

},
linkText:{
  color: COLORS.lightred
}
});
export default styles;