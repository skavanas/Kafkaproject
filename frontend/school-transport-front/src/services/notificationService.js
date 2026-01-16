import axios from "axios";

const API_URL = "http://localhost:8080/api/notifications";

// Récupère les notifications d'un parent
export const getNotificationsByParent = async (parentId) => {
  try {
    const response = await axios.get(`${API_URL}/parent/${parentId}`);
    return response.data;
  } catch (error) {
    console.error(`❌ Error fetching notifications for ${parentId}:`, error);
    return [];
  }
};

// Simule l'envoi d'un ETA (pour tester)
export const sendBusETA = async (eta) => {
  try {
    const response = await axios.post(`${API_URL}/eta`, eta);
    return response.data;
  } catch (error) {
    console.error("❌ Error sending ETA:", error);
    throw error;
  }
};