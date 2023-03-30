import { SignupForm } from './../../types/types';
import { COLORS } from './../../styles/colors';
import { getTabBarHeight } from '@react-navigation/bottom-tabs/lib/typescript/src/views/BottomTabBar';
import { StyleSheet } from 'react-native';
const styles = StyleSheet.create({
    container: {
        flex: 1,
        alignItems: 'center',
        justifyContent: 'space-around',
        // padding: 20,
      },
signUpContainer: {
flex: 1,
alignItems:'center',
justifyContent: 'center',
width: '85%'
},
      title: {
        fontSize: 24,
        fontWeight: 'bold',
        marginBottom: 20,
      },
      input: {
        width: '100%',
        height: 40,
        borderWidth: 1,
        borderColor: '#ccc',
        borderRadius: 5,
        paddingHorizontal: 10,
        marginBottom: 10,
        
      },
      button: {
        backgroundColor: COLORS.lightred,
        borderRadius: 5,
        paddingVertical: 10,
        paddingHorizontal: 20,
        marginTop: 20,
      },
      buttonText: {
        color: '#fff',
        fontWeight: 'bold',
      },
    
      loginContainer:{
          alignItems: 'center',
          justifyContent: 'center',
          marginTop: 10,
 
          width: "100%",
        height: 50,
        flexDirection: 'row'
      },
      loginText:{
        color: COLORS.lightred,
      }
});
export default styles;