import { StyleSheet } from 'react-native';
export const styles = StyleSheet.create({
    container:{
        justifyContent: 'center',
        alignItems: 'center',
        width: "98%",
        backgroundColor: 'grey',
        margin: 5,

    },
    imageContainer:{
        // alignItems: 'center',
        // // justifyContent: 'center',
        width: '99%',
        // flex:1,
        // backgroundColor: 'black'
        flex: 1,
    overflow: 'hidden',
    alignItems: 'center',
    backgroundColor: '#f1f1f5',
    position: 'relative',
    margin: 3
    },
    image:{
        width: 100,
        height: 100,
      flex:1,
      
    }
});