import axios from "axios";

const API_URL = "http://localhost:8080/api/buses";

// Récupère tous les bus
export const getBuses = async () => {
  try {
    const response = await axios.get(API_URL);
    return response.data;
  } catch (error) {
    console.error("❌ Error fetching buses:", error);
    return [];
  }
};

// Récupère un bus spécifique
export const getBusById = async (busId) => {
  try {
    const response = await axios.get(`${API_URL}/${busId}`);
    return response.data;
  } catch (error) {
    console.error(`❌ Error fetching bus ${busId}:`, error);
    return null;
  }
};

// Envoie une nouvelle position (pour simulateur)
export const sendBusLocation = async (busLocation) => {
  try {
    const response = await axios.post(`${API_URL}/location`, busLocation);
    return response.data;
  } catch (error) {
    console.error("❌ Error sending location:", error);
    throw error;
  }
};