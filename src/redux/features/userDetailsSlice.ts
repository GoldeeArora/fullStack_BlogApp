import { User } from './../../types/types';
import { createSlice } from '@reduxjs/toolkit'
import type { PayloadAction } from '@reduxjs/toolkit'
import type { RootState } from '../store'



// Define the initial state using that type
const initialState: User = {
id: '',
name: '',
email: '',
password: '',
about: '',
}

export const userDetailsSlice = createSlice({
  name: 'userDetails',
  // `createSlice` will infer the state type from the `initialState` argument
  initialState,
  reducers: {
    // Use the PayloadAction type to declare the contents of `action.payload`
    enterDetails: (state, action: PayloadAction<User>) => {
      state.id = action.payload.id,
      state.name = action.payload.name,
      state.email = action.payload.email,
      state.password = action.payload.password,
      state.about = action.payload.about
    },
  },
})

export const { enterDetails } = userDetailsSlice.actions

// Other code such as selectors can use the imported `RootState` type
export const selectDetails = (state: RootState) => state;

export default userDetailsSlice.reducer