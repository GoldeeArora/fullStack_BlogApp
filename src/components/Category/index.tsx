import { View, Text } from 'react-native'
import React from 'react'
import { CategoryType } from '../../types/types'
import {styles} from "./styles";
import { useAppSelector } from '../../redux/features/hooks';
import { selectToken } from '../../redux/features/tokenSlice';
export default function Category({categoryId,categorytitle,categoryDescription}: CategoryType):JSX.Element {
  const getToken = useAppSelector(selectToken);
  console.log(getToken)
  return (
    <View style={styles.container}>
      {/* <View style={styles.headingContainer}> */}

      {/* <Text style={styles.text}>{categoryId}</Text> */}
      <Text style={styles.text}>{categorytitle}</Text>
      {/* </View> */}
      <Text>{categoryDescription}</Text>
    </View>
  )
}