import { View, Text, FlatList,Pressable } from 'react-native'
import React,{useEffect, useState} from 'react'
import Post from '../../components/Post'
import { PostType } from '../../types/types'
import { useAppSelector } from '../../redux/features/hooks'
import { selectToken } from '../../redux/features/tokenSlice'
import { selectDetails } from '../../redux/features/userDetailsSlice'
import Ionicons from 'react-native-vector-icons/Ionicons';
import { styles } from './styles'
import { COLORS } from '../../styles/colors'
//@ts-ignore
import {API_URL} from "@env"
import axios from 'axios'
// import { PostType } from 'src/types/types'
export default function MyBlogsScreen(): JSX.Element {
  const [posts,setPosts] = useState<PostType[]>();
  const getToken = useAppSelector(selectToken);
  const getMyDetails = useAppSelector(selectDetails);
  const userId:string = getMyDetails.userDetails.id.toString();
  const getMyBlogs = ()=>{
    axios.get(`${API_URL}/api/user/${userId}/posts`,{
      headers: {
        Authorization: `Bearer ${getToken.token}`
      }
    }).then(res=>
      {
        setPosts(res.data);
      }).catch(e=>console.log(e));
  }
  useEffect(() => {
    getMyBlogs();

  }, [])
  
  return (
   

<View>

    <FlatList 
    data={posts}
    renderItem={({item}: {item: PostType}):JSX.Element=> 
    (
      <View>
    <Post  id={item.postId} imageSource={item.imageName} date={item.addedDate} title={item.title} content={item.content}/>
        </View>
    )
  }
  keyExtractor={(item:PostType,index:number):string=>index.toString()}
  showsHorizontalScrollIndicator={false}
  showsVerticalScrollIndicator={false}
  
  />
  <Pressable style={styles.addIconContainer}>
  <Ionicons name="add-outline" size={33} color="white" />
  </Pressable>
  </View>

  
  )
}